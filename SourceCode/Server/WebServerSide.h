#ifndef __WEBSERVERSIDE__
#define __WEBSERVERSIDE__
#include "TCPListener.h"
#include "HttpHeader.h"

class WebResponse : public TCPListener {
public: 
    WebResponse(const char*ipAddress,int port):TCPListener(ipAddress,port){};
    void AddGetAPI(std::string path,void(*)(HttpRequestHeader&));
    void AddPostAPI(std::string path,void(*)(HttpRequestHeader&));
    void AddPutAPI(std::string path,void(*)(HttpRequestHeader&));
    int run();
private:
    std::map<std::string,void(*)(HttpRequestHeader&)> GETAPI;
    std::map<std::string,void(*)(HttpRequestHeader&)> POSTAPI;
    std::map<std::string,void(*)(HttpRequestHeader&)> PUTAPI;
    int ClientResponse(int client);
    void GETcommand(int client,HttpRequestHeader& rq);
    void POSTcommand(int client,HttpRequestHeader& rq);
    void PUTcommand(int client,HttpRequestHeader& rq);
};
#endif