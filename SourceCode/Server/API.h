#ifndef __AIP_MY_WEB__
#define __AIP_MY_WEB__
#include "HttpHeader.h"
#include "SQLServer-Side.h"

namespace API{
    void CreateUserAccount(HttpRequestHeader&,int);
    void CreateDoctorAccount(HttpRequestHeader&,int);
    void CreateSubAccount(HttpRequestHeader&,int);
    
    void LoginDoc(HttpRequestHeader&,int);
    void LoginPai(HttpRequestHeader&,int);
    void LoginHos(HttpRequestHeader&hd,int);

    void BookAppointment(HttpRequestHeader&,int);

    void GetScheduler_doc(HttpRequestHeader&,int);
    void GetScheduler_pai(HttpRequestHeader&,int); 
    void GetScheduler_doc_pai(HttpRequestHeader&,int);

    void GetPrescriptions(HttpRequestHeader&,int);
    void GivePrescriptions(HttpRequestHeader&,int);
    void RemovePrescriptions(HttpRequestHeader&,int);

    void AddSchedulerDetail(HttpRequestHeader&,int);
    void GetSchedulerDetail(HttpRequestHeader&,int);
    void RemoveSchedulerDetail(HttpRequestHeader&,int);

    void ServiceAdd(HttpRequestHeader&,int);
    void ServiceRemove(HttpRequestHeader&,int);
    void DocServiceAdd(HttpRequestHeader& hd,int client);
    void DocServiceRemove(HttpRequestHeader&,int);

    void GetService(HttpRequestHeader& ,int );
    void SearchService(HttpRequestHeader& ,int);
}
#endif