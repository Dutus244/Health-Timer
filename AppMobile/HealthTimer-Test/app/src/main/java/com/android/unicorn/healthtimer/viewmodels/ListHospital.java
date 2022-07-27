package com.android.unicorn.healthtimer.viewmodels;

import android.app.Application;
import android.graphics.Bitmap;
import android.view.View;

import com.android.unicorn.healthtimer.fragments.BookingSearchListHospitalData;

import java.util.ArrayList;

public class ListHospital{
    private static final ListHospital ourInstance = new ListHospital();
    public static ListHospital getInstance() {
        return ourInstance;
    }
    private ListHospital() {

    }

    private ArrayList<HospitalData> hospitalList = null;

    public ArrayList<HospitalData> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(ArrayList<HospitalData> hospitalList) {
        this.hospitalList = new ArrayList<>();
        for (int i = 0; i < hospitalList.size(); i++){
            this.hospitalList.add(new HospitalData(hospitalList.get(i).getHospitalID(),hospitalList.get(i).getHospitalName(),hospitalList.get(i).getHospitalShortName(),hospitalList.get(i).getHospitalAddress(),hospitalList.get(i).getHospitalDescribe(),hospitalList.get(i).isHospitalIsOn(),hospitalList.get(i).getIconImage(),hospitalList.get(i).getImage(), hospitalList.get(i).getHospitalService()));
        }
    }
}
