#include "SQLServer-Side.h"
#define SQL_RESULT_LEN 240
#define SQL_RETURN_CODE_LEN 1000

void extract_error(
    std::string fn,
    SQLHANDLE handle,
    SQLSMALLINT type)
{
    SQLINTEGER i = 0;
    SQLINTEGER native;
    SQLWCHAR state[7];
    SQLWCHAR text[256];
    SQLSMALLINT len;
    SQLRETURN ret;

    std::cout << "\nThe driver reported the following diagnostics whilst running " << fn << "\n\n";

    do
    {
        ret = SQLGetDiagRecW(type, handle, ++i, state, &native, text,
                             sizeof(text), &len);
        if (SQL_SUCCEEDED(ret))
        {
            printf("%s:%ld:%ld:\n", (char *)state, i, native);
            for (int i = 0; i < len; i++)
            {
                std::cout << char(text[i]);
            }
        }
    } while (ret == SQL_SUCCESS);
}

void SQL_SERVER::freeHandle()
{
    SQLDisconnect(sqlCon);
    SQLFreeHandle(SQL_HANDLE_DBC, sqlCon);
    SQLFreeHandle(SQL_HANDLE_ENV, sqlEvent);
}

bool SQL_SERVER::Connect()
{
    SQLWCHAR retconstring[SQL_RETURN_CODE_LEN];
    if (SQL_SUCCESS != SQLAllocHandle(SQL_HANDLE_ENV, SQL_NULL_HANDLE, &sqlEvent))
    {
        freeHandle();
        return false;
    }
    if (SQL_SUCCESS != SQLSetEnvAttr(sqlEvent, SQL_ATTR_ODBC_VERSION, (SQLPOINTER)SQL_OV_ODBC3, 0))
    {
        freeHandle();
        return false;
    }
    if (SQL_SUCCESS != SQLAllocHandle(SQL_HANDLE_DBC, sqlEvent, &sqlCon))
    {
        freeHandle();
        return false;
    }

    std::cout << "Attempting connection to SQL Server...\n";
    //ODBC Driver 17 for SQL Server
    std::wstring connectString = L"DRIVER={ODBC Driver 17 for SQL Server};SERVER=";
    connectString += serverHost;
    connectString += L" ;DATABASE=";
    connectString += serverName;
    connectString += L" ;UID=";
    connectString += ID;
    connectString += L";PWD=";
    connectString += password;

    switch (SQLDriverConnectW(sqlCon,
                              NULL,
                              (SQLWCHAR *)connectString.c_str(),
                              SQL_NTS,
                              retconstring,
                              1024,
                              NULL,
                              SQL_DRIVER_NOPROMPT))
    {
    case SQL_SUCCESS:
        std::cout << "Successfully connected to SQL Server";
        std::cout << "\n";
        break;
    case SQL_SUCCESS_WITH_INFO:
        std::cout << "Successfully connected to SQL Server";
        std::cout << "\n";
        break;
    case SQL_INVALID_HANDLE:
        std::cout << "Could not connect to SQL Server hander";
        std::cout << "\n";
        extract_error("SQLDriverConnect", sqlCon, SQL_HANDLE_DBC);
        freeHandle();
        return false;
    case SQL_ERROR:
        std::cout << "Could not connect to SQL Server error";
        std::cout << "\n";
        extract_error("SQLDriverConnect", sqlCon, SQL_HANDLE_DBC);
        freeHandle();
        return false;
    default:
        break;
    }
    return true;
}

std::stringstream SQL_SERVER::SelectQuery(const wchar_t *query,SQLLEN &rowscount)
{
    SQLHANDLE sqlStml_;
    SQLAllocHandle(SQL_HANDLE_STMT, sqlCon, &sqlStml_);
    rowscount = 0;
    std::wstring_convert<std::codecvt_utf8<wchar_t>> utf8_conv;
    std::stringstream os;
    if (SQL_SUCCESS != SQLExecDirectW(sqlStml_, (SQLWCHAR *)query, SQL_NTS))
        return os;
    os << "[";
    std::map<int, std::string> colname;

    // declare output variable and pointer
    SQLWCHAR sqlResult[SQL_RESULT_LEN];
    SQLLEN ptrSqlResult;
    SQLUSMALLINT ColumnNumber;
    SQLWCHAR ColumnName[SQL_RESULT_LEN];
    SQLSMALLINT BufferLength;
    SQLSMALLINT NameLengthPtr;
    SQLSMALLINT DataTypePtr;
    SQLULEN ColumnSizePtr;
    SQLSMALLINT DecimalDigitsPtr;
    SQLSMALLINT NullablePtr;
    int k = 1;
    while (SQL_SUCCESS == SQLDescribeColW(sqlStml_, k++, ColumnName, SQL_RESULT_LEN, &NameLengthPtr, &DataTypePtr, &ColumnSizePtr, &DecimalDigitsPtr, &NullablePtr))
    {
        colname[k - 1] = utf8_conv.to_bytes(ColumnName,&ColumnName[NameLengthPtr]);
    }
    SQLLEN temp = 0;
    while (SQLFetch(sqlStml_) == SQL_SUCCESS)
    {
        temp+=1;
        int i = 1;
        os << "{";
        while (SQL_SUCCESS == SQLGetData(sqlStml_, i++, SQL_WCHAR, sqlResult, SQL_RESULT_LEN, &ptrSqlResult))
        {
            os << "\"" << colname[i - 1] << "\":\"";
            os << utf8_conv.to_bytes(sqlResult,&sqlResult[ptrSqlResult/2]);
            os << "\",";
        }
        os.seekp(-1, std::ios_base::cur);
        os << "},";
    }
    rowscount=temp;
    if (rowscount == 0){
        os << "[";
    }
    os.seekp(-1, std::ios_base::cur);
    os << "]";
    SQLFreeHandle(SQL_HANDLE_STMT, sqlStml_);
    return os;
}

SQLLEN SQL_SERVER::DataQuery(const wchar_t* query){
    semaphore.Down();
    SQLHANDLE sqlStml;
    SQLAllocHandle(SQL_HANDLE_STMT, sqlCon, &sqlStml);
    if (SQL_SUCCESS != SQLExecDirectW(sqlStml, (SQLWCHAR *)query, SQL_NTSL)){
        std::wstring_convert<std::codecvt_utf8<wchar_t>> utf8_conv;
        SQLLEN numRecs = 0;
        SQLGetDiagField(SQL_HANDLE_STMT, sqlStml, 0, SQL_DIAG_NUMBER, &numRecs, 0, 0);
        // Get the status records.
        int i = 1;
        SQLWCHAR       SqlState[6],Msg[SQL_MAX_MESSAGE_LENGTH];
        SQLRETURN rc2;
        SQLINTEGER    NativeError;
        SQLSMALLINT MsgLen;
        while (i <= numRecs && (rc2 = SQLGetDiagRecW(SQL_HANDLE_STMT, sqlStml, i, SqlState, &NativeError,
                                                    Msg, sizeof(Msg),&MsgLen)) != SQL_NO_DATA)
        {
            std::cout<< utf8_conv.to_bytes(std::wstring(Msg))<<" | state: ";
            std::cout<< utf8_conv.to_bytes(std::wstring(SqlState))<<'\n';
            i++;
        }
        SQLFreeHandle(SQL_HANDLE_STMT,sqlStml);
        semaphore.Up();
        return -1;
    }
    SQLLEN count = 0; 
    SQLRowCount(sqlStml,&count);
    SQLFreeHandle(SQL_HANDLE_STMT,sqlStml);
    semaphore.Up();
    return count;
}

std::map<int,std::string> SQL_SERVER::Column(const wchar_t* query,SQLLEN &rowscount,int  col){
    SQLHANDLE sqlStml_;
    SQLAllocHandle(SQL_HANDLE_STMT, sqlCon, &sqlStml_);
    rowscount = 0;
    std::wstring_convert<std::codecvt_utf8<wchar_t>> utf8_conv;
    
    if (SQL_SUCCESS != SQLExecDirectW(sqlStml_, (SQLWCHAR *)query, SQL_NTS))
        return std::map<int,std::string>();

    // declare output variable and pointer
    SQLWCHAR sqlResult[SQL_RESULT_LEN];
    SQLLEN ptrSqlResult;
    SQLUSMALLINT ColumnNumber;
    SQLWCHAR ColumnName[SQL_RESULT_LEN];
    SQLSMALLINT BufferLength;
    SQLSMALLINT NameLengthPtr;
    SQLSMALLINT DataTypePtr;
    SQLULEN ColumnSizePtr;
    SQLSMALLINT DecimalDigitsPtr;
    SQLSMALLINT NullablePtr;

    std::map<int,std::string> rs;
    SQLLEN temp = 0;
    while (SQLFetch(sqlStml_) == SQL_SUCCESS)
    {
        if (SQL_SUCCESS == SQLGetData(sqlStml_, col, SQL_WCHAR, sqlResult, SQL_RESULT_LEN, &ptrSqlResult))
        {
            rs[temp] = utf8_conv.to_bytes(sqlResult,&sqlResult[ptrSqlResult/2]);
        }
        temp+=1;
    }

    SQLFreeHandle(SQL_HANDLE_STMT, sqlStml_);
    return rs;
}
