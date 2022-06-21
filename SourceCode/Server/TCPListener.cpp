#include "TCPListener.h"
#include <string>

#ifdef _WIN32
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

#elif __linux__

#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

int TCPListener::init()
{
	int server_fd, new_socket, valread;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = { 0 };
    char* hello = "Hello from server";
 
    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0))
        == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }
 
    // Forcefully attaching socket to the port 8080
    if (setsockopt(server_fd, SOL_SOCKET,
                   SO_REUSEADDR | SO_REUSEPORT, &opt,
                   sizeof(opt))) {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(i_port);
 
    // Forcefully attaching socket to the port 8080
    if (bind(server_fd, (struct sockaddr*)&address,
             sizeof(address))
        < 0) {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }

	i_socket = server_fd;
	return 0;
}

#endif