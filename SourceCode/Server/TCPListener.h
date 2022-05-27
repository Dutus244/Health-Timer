#include <iostream>
#include <WS2tcpip.h>
#include <string>
#include <sstream>
#include <WinSock2.h>
#include "HttpHeader.h"

#pragma comment (lib, "ws2_32.lib")


class TCPListener{
public:
    TCPListener(){}
    TCPListener(const char*ipAddress,int port):i_ipAddress(ipAddress),i_port(port){}
    int init();
protected:
    int i_socket;
private:
    const char* i_ipAddress;
    int i_port;
};

class WebResponse : public TCPListener {
public:
    WebResponse(const char*ipAddress,int port):TCPListener(ipAddress,port){};
    int run();
private:
    static int ClientResponse(int client);
    static void GETcommand(int client,HttpRequestHeader& rq);
    static void POSTcommand(int client,HttpRequestHeader& rq);
    static void PUTcommand(int client,HttpRequestHeader& rq);

};

