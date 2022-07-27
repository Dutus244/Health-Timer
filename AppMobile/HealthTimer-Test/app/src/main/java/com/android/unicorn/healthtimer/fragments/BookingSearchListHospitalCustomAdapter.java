package com.android.unicorn.healthtimer.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.activities.BookingBookActivity;
import com.android.unicorn.healthtimer.activities.ForgotPasswordActivity3;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class BookingSearchListHospitalCustomAdapter extends BaseAdapter {
    private ArrayList<BookingSearchListHospitalData> originList;
    Context context;

    public BookingSearchListHospitalCustomAdapter(Context context, ArrayList<BookingSearchListHospitalData> originList) {
        this.originList=originList;
        this.context=context;
    }

    public ArrayList<BookingSearchListHospitalData> getArrayList() {
        return originList;
    }

    public void setArrayList(ArrayList<BookingSearchListHospitalData> arrayList) {
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
        BookingSearchListHospitalData bookingSearchListHospitalData=originList.get(position);
      if(convertView==null){
          LayoutInflater layoutInflater = LayoutInflater.from(context);
          convertView=layoutInflater.inflate(R.layout.booking_search_list_hospital, null);
          convertView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(context, BookingBookActivity.class);
                  intent.putExtra("name",bookingSearchListHospitalData.HospitalName);
                  context.startActivity(intent);
              }
          });
          TextView name=convertView.findViewById(R.id.booking_search_list_hospital_title);
          TextView address=convertView.findViewById(R.id.booking_search_list_hospital_address);
          ImageView imag=convertView.findViewById(R.id.booking_search_list_hospital_icon);
          name.setText(bookingSearchListHospitalData.HospitalName);
          address.setText(bookingSearchListHospitalData.HospitalAddress);
          Picasso.with(context)
                  .load(bookingSearchListHospitalData.Image)
                  .into(imag);

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
