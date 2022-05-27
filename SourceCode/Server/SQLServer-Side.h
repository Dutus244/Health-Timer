#ifndef _SQL_SERVER_SIDE_
#define _SQL_SERVER_SIDE_
#include <Windows.h>
#include <sql.h>
#include <sqltypes.h>
#include <sqlext.h>

#include <codecvt>
#include <locale>

#include <sstream>

#define UNICODE

class Semaphore {
private:
	int _max;
    int _cur;
public:
	Semaphore(int max,int initialize) {
		_max = max;
        _cur = initialize;
	}
	void Up() {
        if(_cur<_max)
		    _cur++;
	}
	void Down() {
		while (_cur <= 0);
		_cur--;
	}
};


class SQL_SERVER
{
public:
    SQL_SERVER(const wchar_t *_serverHost, const wchar_t *_serverName, const wchar_t *_ID, const wchar_t *_password){
        serverHost = wcsdup(_serverHost);
        serverName = wcsdup(_serverName);
        ID = wcsdup(_ID);
        password = wcsdup(_password);
    }
    bool Connect();
    void Flush(); // clear the query result, ready for next query;
    std::stringstream SelectQuery(const wchar_t *query,SQLLEN *rowscount); // return in json format
    SQLLEN DataQuery(const wchar_t *query); // return the number of edit rows

    ~SQL_SERVER()
    {
        try
        {
            delete[] serverHost;
            delete[] serverName;
            delete[] ID;
            delete[] password;
        }
        catch (...)
        {
        }
        SQLDisconnect(sqlCon);
        freeHandle();
    }

private:
    void freeHandle();
    SQLHANDLE sqlCon;
    SQLHANDLE sqlStml;
    SQLHANDLE sqlEvent;
    const wchar_t *serverHost;
    const wchar_t *serverName;
    const wchar_t *ID;
    const wchar_t *password;
    Semaphore semaphore = Semaphore(1,1);
};

extern SQL_SERVER* dataServer;
#endif