#ifndef _HttpHeader__
#define _HttpHeader__

#include <iostream>
#include <map>
std::string HexConv(std::string str);

class HttpRequestHeader
{
public:
    HttpRequestHeader(std::string request);
    std::string method;
    std::string exfile;
    std::map<std::string, std::string> values;
};

#endif