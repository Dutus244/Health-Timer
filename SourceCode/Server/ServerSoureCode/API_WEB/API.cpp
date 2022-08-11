#include"API.h"
#include<fstream>

#include "../all_var.h"
namespace API{
    std::map<std::string,std::string> DocauthKey;
    std::map<std::string,std::string> DockeyAuth;

    std::map<std::string,std::string> UsauthKey;
    std::map<std::string,std::string> UskeyAuth;

    std::map<std::string,std::string> HosauthKey;
    std::map<std::string,std::string> HoskeyAuth;

    void CreateUserAccount(HttpRequestHeader&hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec CreateUser N'";
        SQLLEN result = -1;
        if (hd.arg["id"] != "" && hd.arg["password"] != "" ){
            query += utf8_conv.from_bytes(hd.arg["id"]) + L"', N'";
            query += utf8_conv.from_bytes(hd.arg["password"]) + L"'";

            result = dataServer->DataQuery(query.c_str());
        }
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            std::string a = "{\"code\":\"success\"";
            a+= ",\"id\":\""+hd.arg["id"]+"\"";
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
        std::wstring query = L"exec CreateDoc '";
        
        query += utf8_conv.from_bytes(HoskeyAuth[hd.arg["auth"]]) + L"', '";
        query += utf8_conv.from_bytes(hd.arg["docID"]) + L"',  N'";
        query += utf8_conv.from_bytes(hd.arg["citizenID"]) + L"', N'";
        query += utf8_conv.from_bytes(hd.arg["name"]) + L"','";
        query += utf8_conv.from_bytes(hd.arg["bthday"]) + L"', N'";
        query += utf8_conv.from_bytes(hd.arg["addr"]) + L"'";


        int result = -1;
        if (hd.arg["docID"] != "" && hd.arg["citizenID"]!=""){
            result = dataServer->DataQuery(query.c_str());
        }
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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

    void EditDoctor(HttpRequestHeader&hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec EditDoc '";
        
        query += utf8_conv.from_bytes(HoskeyAuth[hd.arg["auth"]]) + L"', '";
        query += utf8_conv.from_bytes(hd.arg["docID"]) + L"',  N'";
        query += utf8_conv.from_bytes(hd.arg["citizenID"]) + L"', N'";
        query += utf8_conv.from_bytes(hd.arg["name"]) + L"','";
        query += utf8_conv.from_bytes(hd.arg["bthday"]) + L"', N'";
        query += utf8_conv.from_bytes(hd.arg["addr"]) + L"'";


        int result = -1;
        if (hd.arg["docID"] != "" && hd.arg["citizenID"]!=""){
            result = dataServer->DataQuery(query.c_str());
        }
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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
        
        query += utf8_conv.from_bytes(UsauthKey[hd.arg["auth"]]) + L"', @relationship='";
        query += utf8_conv.from_bytes(hd.arg["relationship"]) + L"'";

        int result = dataServer->DataQuery(query.c_str());

        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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
    
    void LoginPai(HttpRequestHeader&hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from UserLogin (N'";
        query += utf8_conv.from_bytes(hd.arg["id"]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["password"]) + L"')";

        SQLLEN result;
        std::string rs; //doctor ID
        rs = dataServer->Column(query.c_str(),result,1)[0];
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            char auth[16];
            memset(auth,0,16);
            TokenGenerator.GetNextToken(auth);
            if (UsauthKey[rs]!=""){
                UsauthKey.erase(rs);
                UskeyAuth.erase(UsauthKey[rs]); 
            }
            UskeyAuth[auth] = rs;
            UsauthKey[rs] = auth;
            
            std::string a = "{\"code\":\"success\",\"auth\":\""+std::string(auth)+"\"}";
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
    void LoginHosDoc(HttpRequestHeader &hd, int client)
    {
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from HosLogin (N'";
        query += utf8_conv.from_bytes(hd.arg["id"]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["password"]) + L"')";

        SQLLEN result;
        std::string rs; // doctor ID
        rs = dataServer->Column(query.c_str(), result, 1)[0];

        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss << "Access-Control-Allow-Origin: *\r\n";
        oss << "content-type: " << contentType["json"] << "; charset=UTF-8\r\n";
        if (result > 0)
        {
            char auth[16];
            memset(auth, 0, 16);
            TokenGenerator.GetNextToken(auth);
            if (HosauthKey[rs] != "")
            {
                HosauthKey.erase(rs);
                HoskeyAuth.erase(HosauthKey[rs]);
            }
            HoskeyAuth[auth] = rs;
            HosauthKey[rs] = auth;
            std::string a = "{\"code\":\"success\",\"type\":\"hospital\",\"auth\":\"" + std::string(auth) + "\"}";
            oss << "content-length: " << a.size() << "\r\n\r\n";
            oss << a;
            send(client, oss.str().c_str(), oss.str().size(), 0);
        }
        else
        {

            query = L"Select * from DocLogin (N'";
            query += utf8_conv.from_bytes(hd.arg["id"]) + L"' ,N'";
            query += utf8_conv.from_bytes(hd.arg["password"]) + L"')";

            result=0;
            rs = dataServer->Column(query.c_str(), result, 1)[0];

            if (result > 0)
            {
                char auth[16];
                memset(auth, 0, 16);
                TokenGenerator.GetNextToken(auth);
                if (DocauthKey[rs] != "")
                {
                    DocauthKey.erase(rs);
                    DockeyAuth.erase(DocauthKey[rs]);
                }
                DockeyAuth[auth] = rs;
                DocauthKey[rs] = auth;

                std::string a = "{\"code\":\"success\",\"type\":\"doctor\",\"auth\":\"" + std::string(auth) + "\"}";
                oss << "content-length: " << a.size() << "\r\n\r\n";
                oss << a;
                send(client, oss.str().c_str(), oss.str().size(), 0);
            }
            else
            {
                std::string a = "{\"code\":\"fail\"}";
                oss << "content-length: " << a.size() << "\r\n\r\n";
                oss << a;
                send(client, oss.str().c_str(), oss.str().size(), 0);
            }
        }
    }

    void BookAppointment(HttpRequestHeader&hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec CreateScheduler '";
        query += utf8_conv.from_bytes(UskeyAuth[hd.arg["auth"]]) + L"', '";
        query += utf8_conv.from_bytes(hd.arg["time"]) + L"', N'";
        query += utf8_conv.from_bytes(hd.arg["serviceID"]) + L"','";
        query += utf8_conv.from_bytes(hd.arg["hosID"]) + L"'";

        int result =-1;
        if (hd.arg["time"]!="") 
            result = dataServer->DataQuery(query.c_str());

        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            query = L"select orderID from Scheduler where usID = '";
            query += utf8_conv.from_bytes(UskeyAuth[hd.arg["auth"]]) + L"'and a_Time ='";
            query += utf8_conv.from_bytes(hd.arg["time"]) + L"'";
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
        query+= utf8_conv.from_bytes(DockeyAuth[hd.arg["auth"]]) + L"') order by isDone desc, a_Time desc";

        SQLLEN result;
        std::string rs = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            std::string a = "{\"code\":\"success\",\"data\":";
            a+=rs+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }
    void GetScheduler_pai(HttpRequestHeader&hd ,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from PaitentScheduler ('";
        query+= utf8_conv.from_bytes(UskeyAuth[hd.arg["auth"]]) + L"') order by isDone desc, a_Time desc";

        SQLLEN result;
        std::string a = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            a = "{\"code\":\"success\",\"data\":"+a+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }   

    void GetScheduler_doc_pai(HttpRequestHeader&hd ,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from PaitentScheduler ('";
        query+= utf8_conv.from_bytes(hd.arg["usID"]) + L"') order by isDone desc, a_Time desc";

        SQLLEN result;
        std::string a = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            a = "{\"code\":\"success\",\"data\":"+a+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }   
    void GetPrescriptions(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from GetP ('";
        query+= utf8_conv.from_bytes(hd.arg["orderID"]) + L"')";

        SQLLEN result;
        std::string a = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            a = "{\"code\":\"success\",\"data\":" + a +"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }
    void GivePrescriptions(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec AddPrescription '";
        SQLLEN result = -1;
        query += utf8_conv.from_bytes( DockeyAuth[hd.arg["auth"]]) + L"', '";
        query += utf8_conv.from_bytes(hd.arg["orderID"]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["name"]) + L"' ,'";
        query += utf8_conv.from_bytes(hd.arg["amount"]) + L"'";

        result = -1;
        if (hd.arg["name"]!="" && hd.arg["amount"]!="")
            result = dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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

    void RemovePrescriptions(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec RemovePrescription '";
        SQLLEN result = -1;
        query += utf8_conv.from_bytes( DockeyAuth[hd.arg["auth"]]) + L"', '";
        query += utf8_conv.from_bytes(hd.arg["orderID"]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["name"]) + L"' ";

        result = dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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

    void AddSchedulerDetail(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec AddSchedulerDetail '";
        SQLLEN result = -1;
        query += utf8_conv.from_bytes( DockeyAuth[hd.arg["auth"]]) + L"', '";
        query += utf8_conv.from_bytes(hd.arg["orderID"]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["name"]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["value"]) + L"'";

        result = -1;
        if (hd.arg["name"]!="" && hd.arg["value"]!="")
            result = dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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
    
    void AddSchedulerResult(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec AddSchedulerResult '";
        SQLLEN result = -1;
        query += utf8_conv.from_bytes( DockeyAuth[hd.arg["auth"]]) + L"', '";
        query += utf8_conv.from_bytes(hd.arg["orderID"]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["value"]) + L"'";

        result = -1;
        if (hd.arg["name"]!="" && hd.arg["value"]!="")
            result = dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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


    void RemoveSchedulerDetail(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec RemoveSchedulerDetail '";
        SQLLEN result = -1;
        query += utf8_conv.from_bytes( DockeyAuth[hd.arg["auth"]]) + L"', '";
        query += utf8_conv.from_bytes(hd.arg["orderID"]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["name"]) + L"'";

        result = result = dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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

    void GetService(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from GetService () order by hosID";

        SQLLEN result;
        std::string rs = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            std::string a = "{\"code\":\"success\",\"data\":";
            a+=rs+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }

    void SearchService(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from ServiceSearch (N'";

        query+= utf8_conv.from_bytes(hd.arg["searchstr"]) + L"')";
        
        SQLLEN result;
        std::string rs = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            std::string a = "{\"code\":\"success\",\"data\":";
            a+=rs+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }

    void GetSchedulerDetail(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from GetDetail ('";
        query+= utf8_conv.from_bytes(hd.arg["orderID"]) + L"')";

        SQLLEN result;
        std::string a = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            a = "{\"code\":\"success\",\"data\":"+a+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }

        void GetSchedulerResult(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"select (select [name] from [dbo].[DocInfo] e where e.docID =  b.docId ) as doc ,c.[result] from [dbo].[Scheduler] b join [dbo].[Conclusion] c on c.orderID = b.orderID  where b.orderID = '";
        query+= utf8_conv.from_bytes(hd.arg["orderID"]) + L"'";

        SQLLEN result;
        std::string a = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            a = "{\"code\":\"success\",\"data\":"+a+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }


    void ServiceAdd(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec AddService '";
        SQLLEN result = -1;
        query += utf8_conv.from_bytes( HoskeyAuth[hd.arg["auth"]]) + L"', N'";
        query += utf8_conv.from_bytes(hd.arg["serviceID"]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["servicename"]) + L"', N'";
        query += utf8_conv.from_bytes(hd.arg["isOn"]) + L"'";



        result = -1;
        if (hd.arg["serviceID"]!="" && hd.arg["servicename"]!="")
            result = dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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

    void ServiceEdit(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec editService '";
        SQLLEN result = -1;
        query += utf8_conv.from_bytes( HoskeyAuth[hd.arg["auth"]]) + L"', N'";
        query += utf8_conv.from_bytes(hd.arg["serviceID"]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["servicename"]) + L"', N'";
        query += utf8_conv.from_bytes(hd.arg["isOn"]) + L"'";



        result = -1;
        if (hd.arg["serviceID"]!="" && hd.arg["servicename"]!="")
            result = dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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

    void ServiceRemove(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec RemoveService '";
        SQLLEN result = -1;
        query += utf8_conv.from_bytes( HoskeyAuth[hd.arg["auth"]]) + L"', N'";
        query += utf8_conv.from_bytes(hd.arg["serviceID"]) + L"' ";

        result = dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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

    void DocServiceAdd(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec AddDocService '";
        SQLLEN result = -1;
        query += utf8_conv.from_bytes(hd.arg["docID"]) + L"', '";
        query += utf8_conv.from_bytes(HoskeyAuth[hd.arg["auth"]]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["serviceID"]) + L"' ";

        result  = dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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

    void DocServiceRemove(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"exec RemoveDocService '";
        SQLLEN result = -1;
        query += utf8_conv.from_bytes(hd.arg["docID"]) + L"', '";
        query += utf8_conv.from_bytes(HoskeyAuth[hd.arg["auth"]]) + L"' ,N'";
        query += utf8_conv.from_bytes(hd.arg["serviceID"]) + L"' ";

        result = dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
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

    void HosService(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select serviceID,serviceName,isOn from Hos_service where hosID = '";
        query += utf8_conv.from_bytes(HoskeyAuth[hd.arg["auth"]]) + L"'";

        SQLLEN result;
        std::string rs = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            std::string a = "{\"code\":\"success\",\"data\":";
            a+=rs+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }

    void UsHosService(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from GetService () where hosID = '";
        query += utf8_conv.from_bytes(hd.arg["id"]) + L"'";

        SQLLEN result;
        std::string rs = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            std::string a = "{\"code\":\"success\",\"data\":";
            a+=rs+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }
    
    void DocList(HttpRequestHeader& hd,int client){
        std::map<int,std::string> id;

        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from DocOfHos('";
        query += utf8_conv.from_bytes(HoskeyAuth[hd.arg["auth"]]) + L"')";
        SQLLEN result;
        
        std::string rs = dataServer->SelectQuery(query.c_str(),result).str();
    
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            std::stringstream a ;
            a<<"{\"code\":\"success\",\"data\":{\"doclist\":";
            a<<rs;
            a<< ",\"service\":";

            query = L"Select DocID,serviceID from [dbo].[Doc_service] where hosID = '";
            query += utf8_conv.from_bytes(HoskeyAuth[hd.arg["auth"]]) + L"' and  isOn = '1' order by DocID";

            a<<dataServer->SelectQuery(query.c_str(),result).str();

            a<<"}}";
            oss << "content-length: "<<a.str().size()<<"\r\n\r\n";
            oss<<a.str();
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }


    void CheckUser(HttpRequestHeader& hd,int client){
        std::map<int,std::string> id;

        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"Select * from UserAccount where username = N'";
        query += utf8_conv.from_bytes(hd.arg["id"]) + L"'";
        SQLLEN result;

        dataServer->SelectQuery(query.c_str(),result);
    
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            std::stringstream a ;
            a<<"{\"code\":\"Available\"}";
            oss << "content-length: "<<a.str().size()<<"\r\n\r\n";
            oss<<a.str();
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"Unavailable\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }

    void ChangUsPass(HttpRequestHeader& hd,int client){
        std::map<int,std::string> id;

        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"update UserAccount set pass =N'";
        query += utf8_conv.from_bytes(hd.arg["pass"])  + L"' where username = N'";
        query += utf8_conv.from_bytes(UskeyAuth[hd.arg["auth"]]) + L"'";
        SQLLEN result;

        result = dataServer->DataQuery(query.c_str());
    
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            std::stringstream a ;
            a<<"{\"code\":\"success\"}";
            oss << "content-length: "<<a.str().size()<<"\r\n\r\n";
            oss<<a.str();
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"fail\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }

    void HosList(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"select * from [dbo].[HosInfo]";

        SQLLEN result;
        std::string rs = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            std::string a = "{\"code\":\"success\",\"data\":";
            a+=rs+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }


    void UsInfo(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"select * from [dbo].[UserInfo] where usID = '";
        query += utf8_conv.from_bytes(UskeyAuth[hd.arg["auth"]]) + L"'";

        SQLLEN result;
        std::string rs = dataServer->SelectQuery(query.c_str(),result).str();
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>=0){
            std::string a = "{\"code\":\"success\",\"data\":";
            a+=rs+ "}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }

    void EditUsInfo(HttpRequestHeader& hd,int client){
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> utf8_conv;
        std::wstring query = L"update [dbo].[UserInfo] set [name] = N'";
        query += utf8_conv.from_bytes(hd.arg["name"]) + L"', citizenID = '";
        query += utf8_conv.from_bytes(hd.arg["citizenID"]) + L"', addr = N'";
        query += utf8_conv.from_bytes(hd.arg["addr"]) + L"', bthday = '";
        query += utf8_conv.from_bytes(hd.arg["bthday"]) + L"' where usID = '";
        query += utf8_conv.from_bytes(UskeyAuth[hd.arg["auth"]]) + L"'";
        
        SQLLEN result= dataServer->DataQuery(query.c_str());
        std::stringstream oss;
        oss << "HTTP/1.1 200 OK\r\n";
        oss<< "Access-Control-Allow-Origin: *\r\n";
		oss << "content-type: " << contentType["json"]<<"; charset=UTF-8\r\n";
        if (result>0){
            std::string a = "{\"code\":\"success\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
        else{
            std::string a = "{\"code\":\"none\"}";
            oss << "content-length: "<<a.size()<<"\r\n\r\n";
            oss<<a;
            send(client,oss.str().c_str(),oss.str().size(),0);
        }
    }

}