package com.android.unicorn.healthtimer.viewmodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class HospitalData {
    String HospitalID;
    String HospitalName;
    String HospitalShortName;
    String HospitalAddress;
    String HospitalDescribe;
    boolean HospitalIsOn;
    String IconImage;
    String Image;
    ArrayList<HospitalService> HospitalService;

    public HospitalData(String hospitalID, String hospitalName, String hospitalShortName, String hospitalAddress, String hospitalDescribe, boolean hospitalIsOn, String iconImage, String image, ArrayList<HospitalService> hospitalService) {
        HospitalID = hospitalID;
        HospitalName = hospitalName;
        HospitalShortName = hospitalShortName;
        HospitalAddress = hospitalAddress;
        HospitalDescribe = hospitalDescribe;
        HospitalIsOn = hospitalIsOn;
        IconImage = iconImage;
        Image = image;
        HospitalService = hospitalService;
    }

    public String getHospitalID() {
        return HospitalID;
    }

    public void setHospitalID(String hospitalID) {
        HospitalID = hospitalID;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getHospitalShortName() {
        return HospitalShortName;
    }

    public void setHospitalShortName(String hospitalShortName) {
        HospitalShortName = hospitalShortName;
    }

    public String getHospitalAddress() {
        return HospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        HospitalAddress = hospitalAddress;
    }

    public String getHospitalDescribe() {
        return HospitalDescribe;
    }

    public void setHospitalDescribe(String hospitalDescribe) {
        HospitalDescribe = hospitalDescribe;
    }

    public boolean isHospitalIsOn() {
        return HospitalIsOn;
    }

    public void setHospitalIsOn(boolean hospitalIsOn) {
        HospitalIsOn = hospitalIsOn;
    }

    public String getIconImage() {
        return IconImage;
    }

    public void setIconImage(String iconImage) {
        IconImage = iconImage;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public ArrayList<com.android.unicorn.healthtimer.viewmodels.HospitalService> getHospitalService() {
        return HospitalService;
    }

    public void setHospitalService(ArrayList<com.android.unicorn.healthtimer.viewmodels.HospitalService> hospitalService) {
        HospitalService = hospitalService;
    }
}
