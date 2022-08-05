package com.android.unicorn.healthtimer.viewmodels;

public class BookingData {
    String Date;
    String Time;
    String ServiceName;
    String HospitalName;
    Boolean IsDone;
    String OrderID;

    public BookingData(String date, String time, String serviceName, String hospitalName, Boolean isDone, String orderID) {
        Date = date;
        Time = time;
        ServiceName = serviceName;
        HospitalName = hospitalName;
        IsDone = isDone;
        OrderID = orderID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        ServiceName = ServiceName;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public Boolean getDone() {
        return IsDone;
    }

    public void setDone(Boolean done) {
        IsDone = done;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }
}
