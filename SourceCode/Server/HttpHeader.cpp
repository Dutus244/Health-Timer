#include "HttpHeader.h"
#include <sstream>
#include <string>

std::string HexConv(std::string str)
{
    if (str.find('%') == -1)
        return str;
    std::cout << str << "->";
    std::stringstream os(str);
    std::string s;
    std::getline(os, s, '%');
    while (!os.eof())
    {
        char temp[3];
        os.read(temp, 2);
        char c = std::stoul(temp, nullptr, 16);
        s += c;
        std::string e;
        std::getline(os, e, '%');
        s += e;
    }
    std::cout << s << '\n';
    return s;
}

HttpRequestHeader::HttpRequestHeader(std::string coma)
{
    if (coma.compare(0, 3, "GET") == 0)
        this->method = "GET";
    else if (coma.compare(0, 4, "POST") == 0)
        this->method = "POST";
    else if (coma.compare(0, 4, "PATH") == 0)
        this->method = "PATH";
    else if (coma.compare(0, 6, "DELETE") == 0)
        this->method = "DELETE";
    else if (coma.compare(0, 3, "PUT") == 0)
        this->method = "PUT";

    int bg = coma.find("/") + 1;
    int ed = coma.find(" ", bg);

    std::string ctx = coma.substr(bg, ed - bg);
    if (ctx.find("?") != -1)
    {
        // has value
        int sb = ctx.find("?");
        std::stringstream val(ctx.substr(sb, ctx.length() - sb));
        ctx = ctx.substr(0, sb);

        std::string key, value;
        val.ignore(1);
        while (!val.eof())
        {
            std::getline(val, key, '=');
            std::getline(val, value, '&');
            this->arg[HexConv(key)] = HexConv(value);

        }
    }
    ctx = HexConv(ctx);
    this->exfile = ctx;

    std::stringstream sstr(coma);
    std::getline(sstr, ctx);
    while (!sstr.eof())
    {
        std::getline(sstr, ctx);
        int k = ctx.find(":");
        int e = ctx.find("\r");
        if (k != -1)
        {
            this->values[HexConv(ctx.substr(0, k))] = HexConv(ctx.substr(k + 2, e - k - 2));
        }
        else{
            std::stringstream val(ctx);
            std::string key, value;
            while (!val.eof())
            {
                std::getline(val, key, '=');
                std::getline(val, value, '&');
                this->arg[HexConv(key)] = HexConv(value);
            }
        }
    }

    std::stringstream val(values["Cookie"]);
    values.erase("Cookie");
    std::cout << "->" << values["Cookie"] << "<-\n";
    std::string key, value;
    while (!val.eof())
    {
        std::getline(val, key, '=');
        std::getline(val, value, ';');
        this->cookie[key] = value;
        val.ignore(1, ' ');
    }
}