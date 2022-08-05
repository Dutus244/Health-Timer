package com.android.unicorn.healthtimer.fragments;

public class BookingSearchListData {
    String Date;
    String Time;
    String OrderName;
    String HospitalName;
    Boolean IsDone;

    public BookingSearchListData(String date, String time, String orderName, String hospitalName, Boolean isDone) {
        Date = date;
        Time = time;
        OrderName = orderName;
        HospitalName = hospitalName;
        IsDone = isDone;
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

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
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
}
