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

    void BookAppointment(HttpRequestHeader&,int);

    void GetScheduler_doc(HttpRequestHeader&,int);
    void GetScheduler_pai(HttpRequestHeader&,int);    
    void GetPrescriptions(HttpRequestHeader&,int);

    void GivePrescriptions(HttpRequestHeader&,int);
}
#endif