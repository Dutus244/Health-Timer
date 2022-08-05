package com.android.unicorn.healthtimer.viewmodels;

import com.android.unicorn.healthtimer.fragments.BookingSearchListData;

import java.util.ArrayList;

public class UserData {
    private static final UserData ourInstance = new UserData();
    public static UserData getInstance() {
        return ourInstance;
    }
    private UserData() {

    }

    String phone;
    String username;
    String auth;
    ArrayList<BookingData>bookingData = null;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public ArrayList<BookingData> getBookingData() {
        return bookingData;
    }

    public void setBookingData(ArrayList<BookingData> bookingData) {
        this.bookingData = new ArrayList<>();
        for(int i = 0; i < bookingData.size(); i++){
            this.bookingData.add(new BookingData(bookingData.get(i).getDate(),bookingData.get(i).getTime(),bookingData.get(i).getServiceName(),bookingData.get(i).getHospitalName(),bookingData.get(i).getDone(),bookingData.get(i).getOrderID()));
        }
    }
}
