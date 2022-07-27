package com.android.unicorn.healthtimer.fragments;

public class BookingSearchListHospitalData {
    String HospitalName;
    String HospitalAddress;
    String Image;

    public BookingSearchListHospitalData(String HospitalName, String HospitalAddress, String image) {
        this.HospitalName = HospitalName;
        this.HospitalAddress = HospitalAddress;
        this.Image = image;
    }

    public String getHospitalName() {return HospitalName;}

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return HospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        HospitalAddress = hospitalAddress;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
