package com.android.unicorn.healthtimer.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.activities.BookingBookActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class BookingSearchListCustomAdapter extends BaseAdapter {
    private ArrayList<BookingSearchListData> originList;
    private Context context;

    public BookingSearchListCustomAdapter(Context context, ArrayList<BookingSearchListData> originList) {
        this.originList=originList;
        this.context=context;
    }

    public ArrayList<BookingSearchListData> getArrayList() {
        return originList;
    }

    public void setArrayList(ArrayList<BookingSearchListData> arrayList) {
        this.originList.clear();
        this.originList.addAll(arrayList);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return originList.size();
    }

    @Override
    public Object getItem(int position) {
        return originList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookingSearchListData bookingSearchListData=originList.get(position);
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.booking_search_list, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            TextView name=convertView.findViewById(R.id.booking_search_list_hospital_name);
            TextView service=convertView.findViewById(R.id.booking_search_list_hospital_service);
            TextView date=convertView.findViewById(R.id.booking_search_list_hospital_date);
            TextView time=convertView.findViewById(R.id.booking_search_list_hospital_time);
            TextView isdone=convertView.findViewById(R.id.booking_search_list_hospital_isdone);
            name.setText(bookingSearchListData.HospitalName);
            service.setText(bookingSearchListData.ServiceName);
            date.setText(bookingSearchListData.Date);
            time.setText(bookingSearchListData.Time);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            int yearnow = calendar.get(Calendar.YEAR);
            int monthnow  = calendar.get(Calendar.MONTH) + 1;
            int daynow = calendar.get(Calendar.DAY_OF_MONTH);
            int hour24hrs = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);

            String tempdate1 = bookingSearchListData.Date;
            String[] date1 = tempdate1.split("/");
            int day1 = Integer.parseInt(date1[0]);
            int month1 = Integer.parseInt(date1[1]);
            int year1 = Integer.parseInt(date1[2]);

            String temptime1 = bookingSearchListData.Time;
            String[] time1 = temptime1.split(":");
            int hour1 = Integer.parseInt(time1[0]);
            int minute1 = Integer.parseInt(time1[1]);

            if (yearnow > year1) {
                if (bookingSearchListData.IsDone == Boolean.TRUE){
                    isdone.setText("Đã đi khám");
                }
                else {
                    isdone.setText("Đã trễ hẹn");
                }
            }
            else if (yearnow == year1) {
                if (monthnow > month1){
                    if (bookingSearchListData.IsDone == Boolean.TRUE){
                        isdone.setText("Đã đi khám");
                    }
                    else {
                        isdone.setText("Đã trễ hẹn");
                    }
                }
                else if (monthnow == month1) {
                    if (daynow > day1){
                        if (bookingSearchListData.IsDone == Boolean.TRUE){
                            isdone.setText("Đã đi khám");
                        }
                        else {
                            isdone.setText("Đã trễ hẹn");
                        }
                    }
                    else if (daynow == day1){
                        if (hour24hrs > hour1) {
                            if (bookingSearchListData.IsDone == Boolean.TRUE){
                                isdone.setText("Đã đi khám");
                            }
                            else {
                                isdone.setText("Đã trễ hẹn");
                            }
                        }
                        else if (hour24hrs == hour1){
                            if (minutes > minute1){
                                if (bookingSearchListData.IsDone == Boolean.TRUE){
                                    isdone.setText("Đã đi khám");
                                }
                                else {
                                    isdone.setText("Đã trễ hẹn");
                                }
                            }
                            else {
                                if (bookingSearchListData.IsDone == Boolean.TRUE){
                                    isdone.setText("Đã đi khám");
                                }
                                else {
                                    isdone.setText("Chưa đi khám");
                                }
                            }
                        }
                        else {
                            if (bookingSearchListData.IsDone == Boolean.TRUE){
                                isdone.setText("Đã đi khám");
                            }
                            else {
                                isdone.setText("Chưa đi khám");
                            }
                        }
                    }
                    else {
                        if (bookingSearchListData.IsDone == Boolean.TRUE){
                            isdone.setText("Đã đi khám");
                        }
                        else {
                            isdone.setText("Chưa đi khám");
                        }
                    }
                }
                else {
                    if (bookingSearchListData.IsDone == Boolean.TRUE){
                        isdone.setText("Đã đi khám");
                    }
                    else {
                        isdone.setText("Chưa đi khám");
                    }
                }
            }
            else {
                if (bookingSearchListData.IsDone == Boolean.TRUE){
                    isdone.setText("Đã đi khám");
                }
                else {
                    isdone.setText("Chưa đi khám");
                }
            }
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public int getViewTypeCount() {
        if(getCount()<1) return 1;
        return getCount();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
