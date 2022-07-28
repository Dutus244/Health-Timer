#include "TCPListener.h"
#include <string>

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