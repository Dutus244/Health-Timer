package com.android.unicorn.healthtimer.viewmodels;

import java.util.ArrayList;

public class ListBooking {
    private static final ListBooking ourInstance = new ListBooking();
    public static ListBooking getInstance() {
        return ourInstance;
    }
    private ListBooking() {

    }

    private ArrayList<BookingData> bookingList = null;

    public ArrayList<BookingData> getBookingList() {
        return bookingList;
    }

    public void setBookingList(ArrayList<BookingData> bookingList) {
        this.bookingList = new ArrayList<>();
        for (int i = 0; i < bookingList.size(); i++){
            this.bookingList.add(new BookingData(bookingList.get(i).getDate(),bookingList.get(i).getTime(),bookingList.get(i).getServiceName(),bookingList.get(i).getHospitalName(),bookingList.get(i).getDone(),bookingList.get(i).getOrderID()));
        }
    }
}
