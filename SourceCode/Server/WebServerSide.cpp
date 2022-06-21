#include"WebServerSide.h"
#include <string>
#include <thread>
#include <sstream>
#include <fstream>
#include <algorithm>
#include <map>

#define WEBFLD "WEB/"


char asciitolower(char in)
{
	if (in <= 'Z' && in >= 'A')
		return in - ('Z' - 'z');
	return in;
}

void WebResponse::GETcommand(int client, HttpRequestHeader &rq)
{
	if (int(GETAPI[rq.exfile]) != 0)
	{
		GETAPI[rq.exfile](rq,client);
	}
	else
	{
		std::string fileName = rq.exfile;
		if (fileName.size() == 0 || fileName.find_last_of('/') == fileName.size() - 1)
		{ // resquest the home page
			fileName += "index.html";
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
			oss << "Content-Length: 35\r\n";
			oss << "\r\n";
			oss << "<HTML><h1>404 Not Found</h1></HTML>";
			send(client, oss.str().c_str(), oss.str().size() + 1, 0);
		}
	}
}

void WebResponse::POSTcommand(int client, HttpRequestHeader &rq)
{
	if (int(POSTAPI[rq.exfile]) != 0)
	{
		POSTAPI[rq.exfile](rq,client);
	}
	else
	{
		std::stringstream oss;
		oss << "HTTP/1.1 204 No Content\r\n";
		oss << "content-type: text/html; charset=UTF-8\r\n";
		oss << "content-length: 0\r\n\r\n";
		send(client, oss.str().c_str(), oss.str().size() + 1, 0);
	}
}

void WebResponse::PUTcommand(int client, HttpRequestHeader &rq)
{
	if (int(PUTAPI[rq.exfile]) != 0)
	{
		PUTAPI[rq.exfile](rq,client);
	}
	else
	{
		std::stringstream oss;
		oss << "HTTP/1.1 204 No Content\r\n";
		oss << "content-type: text/html; charset=UTF-8\r\n";
		oss << "content-length: 0\r\n\r\n";
		send(client, oss.str().c_str(), oss.str().size() + 1, 0);
	}
}

int WebResponse::ClientResponse(int client)
{
	char buff[65536];
	ZeroMemory(buff, 65536);
#ifdef _WIN32
	while (recv(client, buff, 65536, 0) > 0)
	{
		HttpRequestHeader rq(buff);
		std::cout<<rq.method<<'\t'<<rq.exfile<<'\n';
		std::cout<<buff<<"\n\n";
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
		else{
			std::stringstream oss;
			oss << "HTTP/1.1 204 No Content\r\n";
			oss << "content-type: text/html; charset=UTF-8\r\n";
			oss << "content-length: 0\r\n\r\n";
		send(client, oss.str().c_str(), oss.str().size() + 1, 0);
		}
	}
#elif __linux__
	while (read(client, buff, 65536, 0) > 0)
	{
		HttpRequestHeader rq(buff);
		std::cout << rq.method << '\t' << rq.exfile << '\n';
		std::cout << buff << "\n\n";
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
		else
		{
			std::stringstream oss;
			oss << "HTTP/1.1 204 No Content\r\n";
			oss << "content-type: text/html; charset=UTF-8\r\n";
			oss << "content-length: 0\r\n\r\n";
			send(client, oss.str().c_str(), oss.str().size() + 1, 0);
		}
	}
#endif

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
		std::thread a = std::thread(&WebResponse::ClientResponse,this,client);
		a.detach();
	}
	WSACleanup();
	return 0;
}

void WebResponse::AddGetAPI(std::string path,void(*Func)(HttpRequestHeader&,int)){
	this->GETAPI[path] = Func;
}
void WebResponse::AddPostAPI(std::string path,void(*Func)(HttpRequestHeader&,int)){
	this->POSTAPI[path] = Func;
}
void WebResponse::AddPutAPI(std::string path,void(*Func)(HttpRequestHeader&,int)){
	this->PUTAPI[path] = Func;
}