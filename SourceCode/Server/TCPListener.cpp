#include "TCPListener.h"
#include <string>
#include <thread>
#include <sstream>
#include <fstream>
#include <algorithm>
#include <map>
#include "SQLServer-Side.h"
#define WEBFLD "WEB/"


// std::stringstream sendtutu (){
//  SQLHANDLE sqlCon;
//     SQLHANDLE sqlStml;
//     SQLHANDLE sqlEvent;
//     SQLWCHAR retconstring[SQL_RETURN_CODE_LEN];

//     sqlCon = nullptr;
//     sqlStml = nullptr;
//   std::wstring_convert<std::codecvt_utf8<wchar_t>> utf8_conv;
//     if (SQL_SUCCESS != SQLAllocHandle(SQL_HANDLE_ENV,SQL_NULL_HANDLE,&sqlEvent))
//         goto COMPLETED;
//     if (SQL_SUCCESS != SQLSetEnvAttr(sqlEvent,SQL_ATTR_ODBC_VERSION,(SQLPOINTER)SQL_OV_ODBC3,0)) 
//         goto COMPLETED;
//     if (SQL_SUCCESS != SQLAllocHandle(SQL_HANDLE_DBC,sqlEvent,&sqlCon))
//         goto COMPLETED;
//     std::cout << "Attempting connection to SQL Server...";
//     std::cout << "\n";
//     switch (SQLDriverConnectW(sqlCon,
//                              NULL,
//                              (SQLWCHAR*)L"DRIVER={ODBC Driver 17 for SQL Server};SERVER=tcp:tntruong1009.database.windows.net ,1433;DATABASE=DB20clc4_Test05-07_SieuTriTue;UID=tntruong1009;PWD=Truong1009;",
//                              //(SQLWCHAR*)L"DRIVER={ODBC Driver 17 for SQL Server};Data Source=localhost ,1433;DATABASE=master;UID=as;PWD=truong1009;",
//                              //(SQLWCHAR *)L"Server=tcp:tntruong1009.database.windows.net,1433;Initial Catalog=DB20clc4_Test05-07_SieuTriTue;Persist Security Info=False;User ID=tntruong1009;Password=Truong1009;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;",
//                              SQL_NTS,
//                              retconstring,
//                              1024,
//                              NULL,
//                              SQL_DRIVER_NOPROMPT))
//     {
//     case SQL_SUCCESS:
//         std::cout << "Successfully connected to SQL Server";
//         std::cout << "\n";
//         break;
//     case SQL_SUCCESS_WITH_INFO:
//         std::cout << "Successfully connected to SQL Server";
//         std::cout << "\n";
//         break;
//     case SQL_INVALID_HANDLE:
//         std::cout << "Could not connect to SQL Server hander";
//         std::cout << "\n";
//         extract_error("SQLDriverConnect", sqlCon, SQL_HANDLE_DBC);
//         goto COMPLETED;
//     case SQL_ERROR:
//         std::cout << "Could not connect to SQL Server error";
//         std::cout << "\n";
//         extract_error("SQLDriverConnect", sqlCon, SQL_HANDLE_DBC);
//         goto COMPLETED;
//     default:
//         break;
//     }
//     // if there is a problem connecting then exit application
//     if (SQL_SUCCESS != SQLAllocHandle(SQL_HANDLE_STMT, sqlCon, &sqlStml))
//         goto COMPLETED;
//     // output
//     std::cout << "\n";
//     std::cout << "Executing T-SQL query...";
//     std::cout << "\n";
//     // if there is a problem executing the query then exit application
//     // else display query result
//     if (SQL_SUCCESS != SQLExecDirectW(sqlStml, (SQLWCHAR *)L"SELECT * from THISINH", SQL_NTS))
//     {
//         std::cout << "Error querying SQL Server";
//         std::cout << "\n";
//         goto COMPLETED;
//     }
//     else
//     {

//         std::stringstream os;
//         os <<"{\"list\": [";
//         std::map<int,std::string> colname;

//         // declare output variable and pointer
//         SQLWCHAR sqlVersion[SQL_RESULT_LEN];
//         SQLLEN ptrSqlVersion;
//             SQLUSMALLINT ColumnNumber;
//             SQLWCHAR ColumnName[SQL_RESULT_LEN];
//             SQLSMALLINT BufferLength;
//             SQLSMALLINT NameLengthPtr;
//             SQLSMALLINT DataTypePtr;
//             SQLULEN ColumnSizePtr;
//             SQLSMALLINT DecimalDigitsPtr;
//             SQLSMALLINT NullablePtr;
//             int k = 1;
//             while (SQL_SUCCESS == SQLDescribeColW(sqlStml, k++, ColumnName, SQL_RESULT_LEN, &NameLengthPtr, &DataTypePtr, &ColumnSizePtr, &DecimalDigitsPtr, &NullablePtr))
//             {
//                 colname[k-1] =  utf8_conv.to_bytes(std::wstring(ColumnName));
//             }
//         while (SQLFetch(sqlStml) == SQL_SUCCESS)
//         {

//             int i = 1;
//             os<<"{";
//             while(SQL_SUCCESS==SQLGetData(sqlStml, i++, SQL_WCHAR, sqlVersion, SQL_RESULT_LEN, &ptrSqlVersion))
//             {
//                 os<<"\""<< colname[i-1]<<"\":\"";
//                 os<< utf8_conv.to_bytes(std::wstring(sqlVersion));
//                 os<<"\",";
//             }
//             os.seekp(-1,std::ios_base::cur);
//             os<<"},";
//         }
//         os.seekp(-1,std::ios_base::cur);
//         os<<"]}";
//         return os;
//     }
// // close connection and free resources
// COMPLETED:
//     SQLFreeHandle(SQL_HANDLE_STMT, sqlStml);
//     SQLDisconnect(sqlCon);
//     SQLFreeHandle(SQL_HANDLE_DBC, sqlCon);
//     SQLFreeHandle(SQL_HANDLE_ENV, sqlEvent);
//     // pause the console window - exit when key is pressed	
// 	std::stringstream s;
// 	return s;
// }




std::map<std::string, std::string> contentType = {
	{"pdf", "application/pdf"},
	{"txt", "text/plain"},
	{"html", "text/html; charset=UTF-8"},
	{"json", "application/json"},
	{"exe", "application/octet-stream"},
	{"zip", "application/zip"},
	{"doc", "application/msword"},
	{"xls", "application/vnd.ms-excel"},
	{"ppt", "application/vnd.ms-powerpoint"},
	{"gif", "image/gif"},
	{"png", "image/png"},
	{"jpeg", "image/jpg"},
	{"jpg", "image/jpg"},
	{"php", "text/plain"},
	{"css", "text/css"}};

char asciitolower(char in)
{
	if (in <= 'Z' && in >= 'A')
		return in - ('Z' - 'z');
	return in;
}

int TCPListener::init()
{
	// Initialze winsock
	WSADATA wsData;
	WORD ver = MAKEWORD(2, 2);

	int wsOk = WSAStartup(ver, &wsData);

	if (wsOk != 0)
	{
		std::cout << "WSAStartup fail";
		return -1;
	}

	addrinfo hints;
	ZeroMemory(&hints, sizeof(hints));

	hints.ai_family = AF_INET;
	hints.ai_socktype = SOCK_STREAM;
	hints.ai_protocol = IPPROTO_TCP;
	hints.ai_flags = AI_PASSIVE;

	addrinfo *sock = nullptr;

	int addr = getaddrinfo(nullptr, std::to_string(i_port).c_str(), &hints, &sock);
	if (addr != 0)
	{
		std::cout << "Get Addrinfo fail";
		return -1;
	}

	int listenSock = socket(sock->ai_family, sock->ai_socktype, sock->ai_protocol);
	if (listenSock == INVALID_SOCKET)
	{
		std::cout << "create listen Fail" << WSAGetLastError();
		freeaddrinfo(sock);
		WSACleanup();
		return -1;
	}

	int bl = bind(listenSock, sock->ai_addr, (int)sock->ai_addrlen);
	if (bl == SOCKET_ERROR)
	{
		std::cout << "bind fail: " << WSAGetLastError();
		freeaddrinfo(sock);
		WSACleanup();
		return -1;
	}

	i_socket = listenSock;

	return 0;
}

void WebResponse::GETcommand(int client, HttpRequestHeader &rq)
{
	std::string fileName = rq.exfile;
	if (fileName.size()==0 || fileName.find_last_of('/') == fileName.size()-1 )
	{ // resquest the home page
		fileName +="index.html";
	}
	fileName = HexConv(fileName);
	std::ifstream ifs(WEBFLD + fileName, std::iostream::binary);
	int filesize = 0;
	if (ifs.is_open())
	{
		ifs.seekg(0, std::ios_base::end);
		filesize = ifs.tellg();
		ifs.seekg(0, std::ios_base::beg);
		int dot = fileName.find_last_of('.') + 1;
		std::string tail = fileName.substr(dot);

		std::transform(tail.begin(), tail.end(), tail.begin(), asciitolower);

		std::stringstream oss;
		oss << "HTTP/1.1 200 OK\r\n";
		oss << "Content-Type: " << contentType[tail] << "\r\n";
		oss << "Content-Length: " << filesize << "\r\n";
		oss << "Set-Cookie: tada=11000\r\n";
		oss << "\r\n";
		oss << ifs.rdbuf();
		ifs.close();
		send(client, oss.str().c_str(), oss.str().size() + 1, 0);
	}
	else
	{
		std::stringstream oss;
		oss << "HTTP/1.1 404 Not Found\r\n";
		oss << "Content-Type: text/html\r\n";
		oss << "Content-Length: 22\r\n";
		oss << "\r\n";
		oss << "<h1>404 Not Found</h1>";
		send(client, oss.str().c_str(), oss.str().size() + 1, 0);
	}
}

void WebResponse::POSTcommand(int client, HttpRequestHeader &rq)
{
	    // SQL_SERVER sv(L"tcp:tntruong1009.database.windows.net",
        //           L"DB20clc4_Test05-07_SieuTriTue",
        //           L"tntruong1009",
        //           L"Truong1009");
		// sv.Connect();
		SQLLEN count;
		std::stringstream temp = dataServer->SelectQuery(L"SELECT * from THISINH",&count);
		dataServer->Flush();
		std::cout<<count<<'\n';
		std::stringstream oss;
		oss << "HTTP/1.1200 Not Found\r\n";
		oss << "Content-Type: application/json; charset=UTF-8\r\n";
		oss << "Content-Length: "<<temp.str().size()<<"\r\n";
		oss << "\r\n";
		send(client, oss.str().c_str(), oss.str().size(), 0);
		send(client,temp.str().c_str(),temp.str().size()+1,0);

}

void WebResponse::PUTcommand(int client, HttpRequestHeader &rq)
{
}

int WebResponse::ClientResponse(int client)
{
	char buff[65536];
	ZeroMemory(buff, 65536);
	while (recv(client, buff, 65536, 0) > 0)
	{
		std::cout<<buff<<"\n";
		HttpRequestHeader rq(buff);
		if (rq.method == "GET")
		{
			GETcommand(client, rq);
		}
		else if (rq.method == "POST")
		{
			POSTcommand(client, rq);
		}
		else if (rq.method == "PUT")
		{
			PUTcommand(client, rq);
		}
	}
	return 0;
}

int WebResponse::run()
{
	bool running = true;
	if (listen(i_socket, 1000) == SOCKET_ERROR)
	{
		std::cout << "listen fail";
		return -1;
	};
	while (running)
	{
		int client = accept(i_socket, nullptr, nullptr);
		std::thread a = std::thread(WebResponse::ClientResponse, client);
		a.detach();
	}
	WSACleanup();
	return 0;
}