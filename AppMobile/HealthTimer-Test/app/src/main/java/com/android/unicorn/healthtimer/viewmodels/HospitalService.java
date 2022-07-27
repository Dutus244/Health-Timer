package com.android.unicorn.healthtimer.viewmodels;

public class HospitalService {
    String ServiceName;
    String ServiceID;

    public HospitalService(String serviceName, String serviceID){
        this.ServiceName = serviceName;
        this.ServiceID = serviceID;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String serviceID) {
        ServiceID = serviceID;
    }
}
