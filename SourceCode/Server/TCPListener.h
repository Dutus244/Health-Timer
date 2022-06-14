#ifndef __TCPLISTENER__
#define __TCPLISTENER__
#include <iostream>
#include <WinSock2.h>
#include <WS2tcpip.h>
#include <Windows.h>
#pragma comment(lib, "ws2_32.lib")

class TCPListener
{
public:
    TCPListener() {}
    TCPListener(const char *ipAddress, int port) : i_ipAddress(ipAddress), i_port(port) {}
    int init();

protected:
    int i_socket;

private:
    const char *i_ipAddress;
    int i_port;
};

#endif