#include"API.h"
#include<fstream>

#include "all_var.h"
namespace API{
    std::map<std::string,std::string> authKey;
    std::map<std::string,std::string> keyAuth;
    std::map<std::string,std::string> hostAuth;

    void CreateUserAccount(HttpRequestHeader&hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec CreateAccount @account=N'";
        query += utf8_conv.from_bytes(hd.arg["account"]) + L"', @password=N'";
        query += utf8_conv.from_bytes(hd.arg["password"]) + L"'";

        int result = dataServer->DataQuery(query.c_str());

        std::stringstream oss;
        oss << "HTTP/1.1 204 No Content\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            std::string a = "{\"code\":\"success\"";
            a+= ",\"account\":\""+hd.arg["account"]+"\"";
            a+= ",\"password\":\""+hd.arg["password"]+"\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"fail\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }
    void CreateDoctorAccount(HttpRequestHeader&hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::cout<<hd.arg["account"]<<"  "<<hd.arg["password"]<<"\n";
        std::wstring query = L"exec AddDoctor @hospital='";
        
        query += utf8_conv.from_bytes(hd.arg["hosID"]) + L"', @service='";
        query += utf8_conv.from_bytes(hd.arg["service"]) + L"', @name = N'";
        query += utf8_conv.from_bytes(hd.arg["name"]) + L"'";

        int result = dataServer->DataQuery(query.c_str());

        std::stringstream oss;
        oss << "HTTP/1.1 204 No Content\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            std::string a = "{\"code\":\"success\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"fail\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }
    void CreateSubAccount(HttpRequestHeader&hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
         std::wstring query = L"exec CreateSubAccount @owner='";
        
        query += utf8_conv.from_bytes(authKey[hd.cookie["auth"]]) + L"', @relationship='";
        query += utf8_conv.from_bytes(hd.arg["relationship"]) + L"'";

        int result = dataServer->DataQuery(query.c_str());

        std::stringstream oss;
        oss << "HTTP/1.1 204 No Content\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            std::string a = "{\"code\":\"success\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"fail\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }
    void LoginDoc(HttpRequestHeader&hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from Doctor where ID=N'";
        query += utf8_conv.from_bytes(hd.arg["id"]) + L"'and [Password]=N'";
        query += utf8_conv.from_bytes(hd.arg["password"]) + L"'and [hospital] = '";
        query += utf8_conv.from_bytes(hd.cookie["hosID"]) + L"' COLLATE Latin1_General_CS_AS";

        SQLLEN result;
        dataServer->SelectQuery(query.c_str(),result);
        std::stringstream oss;
        oss << "HTTP/1.1 204 No Content\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            char auth[16];
            TokenGenerator.GetNextToken(auth);
            hostAuth[auth] = hd.cookie["hosID"] +std::string(".") + hd.arg["id"];

            std::cout<<hostAuth[auth]<<"\n";
            oss <<"set-cookie: auth = "<<auth<<"\r\n";
            
            std::string a = "{\"code\":\"success\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"fail\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }
    void LoginPai(HttpRequestHeader&hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from Account where ID=N'";
        query += utf8_conv.from_bytes(hd.arg["id"]) + L"'and [password]=N'";
        query += utf8_conv.from_bytes(hd.arg["password"]) + L"' COLLATE Latin1_General_CS_AS";

        SQLLEN result;
        dataServer->SelectQuery(query.c_str(),result);
        std::stringstream oss;
        oss << "HTTP/1.1 204 No Content\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            char auth[16];
            ZeroMemory(auth,16);
            TokenGenerator.GetNextToken(auth);

            if (keyAuth[hd.arg["id"]]!=""){
                authKey.erase(keyAuth[hd.arg["id"]]);
            }
            oss <<"set-cookie: auth="<<auth<<"\r\n";
            authKey[auth] = hd.arg["id"];
            keyAuth[hd.arg["id"]] = auth;

            std::cout<<auth<<'\n';
            std::string a = "{\"code\":\"success\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"fail\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }
    void BookAppointment(HttpRequestHeader&hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec BookA @guessID=N'";
        query += utf8_conv.from_bytes(authKey["auth"]) + L"', @hospitalID=N'";
        query += utf8_conv.from_bytes(hd.cookie["hosID"]) + L"', @service=";
        query += utf8_conv.from_bytes(hd.arg["serviceID"]) + L"', @date =";
        query += utf8_conv.from_bytes(hd.cookie["date"]) + L"', @time =";
        query += utf8_conv.from_bytes(hd.cookie["time"]) + L"'";


        int result = dataServer->DataQuery(query.c_str());

        std::stringstream oss;
        oss << "HTTP/1.1 204 No Content\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            query = L"select ID from Scheduler where GuessID = '";
            query += utf8_conv.from_bytes(authKey["auth"]) + L"'and [date] =";
            query += utf8_conv.from_bytes(hd.cookie["date"]) + L"'and [time] =";
            query += utf8_conv.from_bytes(hd.cookie["time"]) + L"'";
            SQLLEN temp;
            std::stringstream ID; 
            ID<< dataServer->SelectQuery(query.c_str(),temp).rdbuf();

            std::string a = "{\"code\":\"success\",\"data\":";
            a+=ID.str()+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"fail\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }
    void GetScheduler_doc(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from DocScheduler ('";
        query+= utf8_conv.from_bytes(hd.cookie["auth"]) + L"')";

        SQLLEN result;
        std::string a = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"fail\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }
    void GetScheduler_pai(HttpRequestHeader&,int){}   
    void GetPrescriptions(HttpRequestHeader&,int){}
    void GivePrescriptions(HttpRequestHeader&,int){};
}