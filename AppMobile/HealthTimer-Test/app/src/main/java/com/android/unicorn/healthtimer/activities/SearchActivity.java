package com.android.unicorn.healthtimer.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.fragments.BookingSearchListCustomAdapter;
import com.android.unicorn.healthtimer.fragments.BookingSearchListData;
import com.android.unicorn.healthtimer.fragments.BookingSearchListHospitalCustomAdapter;
import com.android.unicorn.healthtimer.fragments.BookingSearchListHospitalData;
import com.android.unicorn.healthtimer.fragments.RecordListCustomAdapter;
import com.android.unicorn.healthtimer.viewmodels.BookingData;
import com.android.unicorn.healthtimer.viewmodels.ListHospital;
import com.android.unicorn.healthtimer.viewmodels.UserData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity implements TextWatcher {
    private ListView list;
    private EditText search;
    private BookingSearchListHospitalCustomAdapter customAdapter1;
    private BookingSearchListCustomAdapter customAdapter2;
    private RecordListCustomAdapter customAdapter3;
    private ArrayList<BookingSearchListHospitalData> results1;
    private ArrayList<BookingSearchListData> results2;
    private ArrayList<BookingSearchListData> results3;
    private int format = 1;
    private ArrayList<BookingData> bookingData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search = findViewById(R.id.activity_search_search);
        search.addTextChangedListener(this);
        UserData userData = UserData.getInstance();
        bookingData = new ArrayList<>();
        bookingData = userData.getBookingData();
        list = findViewById(R.id.activity_search_list);

        ListHospital listHospital_BookingSearch = ListHospital.getInstance();

        ArrayList<BookingSearchListHospitalData> temp = new ArrayList<>();
        for(int i = 0; i < listHospital_BookingSearch.getHospitalList().size(); i++){
            temp.add(new BookingSearchListHospitalData(listHospital_BookingSearch.getHospitalList().get(i).getHospitalName(), listHospital_BookingSearch.getHospitalList().get(i).getHospitalAddress(),listHospital_BookingSearch.getHospitalList().get(i).getIconImage()));
        }

        customAdapter1 = new BookingSearchListHospitalCustomAdapter(SearchActivity.this, temp);
        list.setAdapter(customAdapter1);

        TextView texthospital = findViewById(R.id.activity_search_hospital_list);
        texthospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                format = 1;
                ArrayList<BookingSearchListHospitalData> temp = new ArrayList<>();
                for(int i = 0; i < listHospital_BookingSearch.getHospitalList().size(); i++){
                    temp.add(new BookingSearchListHospitalData(listHospital_BookingSearch.getHospitalList().get(i).getHospitalName(), listHospital_BookingSearch.getHospitalList().get(i).getHospitalAddress(),listHospital_BookingSearch.getHospitalList().get(i).getIconImage()));
                }

                customAdapter1 = new BookingSearchListHospitalCustomAdapter(SearchActivity.this, temp);
                list.setAdapter(customAdapter1);
            }
        });

        TextView textbooking = findViewById(R.id.activity_search_booking_list);
        textbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                format = 2;
                if (bookingData.size() != 0){


                    ArrayList<BookingSearchListData> temp = new ArrayList<>();
                    for(int i = 0; i < bookingData.size(); i++){
                        temp.add(new BookingSearchListData(bookingData.get(i).getOrderID(), bookingData.get(i).getDate(),bookingData.get(i).getTime(),bookingData.get(i).getServiceName(),bookingData.get(i).getHospitalName(),bookingData.get(i).getDone()));
                    }

                    Collections.sort(temp, new Comparator<BookingSearchListData>() {
                        @Override
                        public int compare(BookingSearchListData lhs, BookingSearchListData rhs) {
                            String tempdate1 = lhs.getDate();
                            String[] date1 = tempdate1.split("/");
                            String tempdate2 = rhs.getDate();
                            String[] date2 = tempdate2.split("/");

                            String temptime1 = lhs.getTime();
                            String[] time1 = temptime1.split(":");
                            int result1 = Integer.parseInt(time1[0]);
                            String temptime2 = lhs.getTime();
                            String[] time2 = temptime2.split(":");
                            int result2 = Integer.parseInt(time2[0]);

                            if (date1[2].compareTo(date2[2]) != 0){
                                return date1[2].compareTo(date2[2]);
                            }
                            if (date1[1].compareTo(date2[1]) != 0){
                                return date1[1].compareTo(date2[1]);
                            }
                            if (date1[0].compareTo(date2[0]) != 0){
                                return date1[0].compareTo(date2[0]);
                            }
                            if (result1 > result2){
                                return 1;
                            }
                            return -1;
                        }
                    });

                    customAdapter2 = new BookingSearchListCustomAdapter(SearchActivity.this, temp);
                    list.setAdapter(customAdapter2);
                }
                else {

                }
            }
        });

        TextView textrecord = findViewById(R.id.activity_search_record_list);
        textrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                format = 3;
                if (bookingData.size() != 0){
                    ArrayList<BookingSearchListData> temp = new ArrayList<>();
                    for(int i = 0; i < bookingData.size(); i++){
                        if (bookingData.get(i).getDone() == Boolean.TRUE){
                            temp.add(new BookingSearchListData(bookingData.get(i).getOrderID(), bookingData.get(i).getDate(),bookingData.get(i).getTime(),bookingData.get(i).getServiceName(),bookingData.get(i).getHospitalName(),bookingData.get(i).getDone()));
                        }
                    }

                    Collections.sort(temp, new Comparator<BookingSearchListData>() {
                        @Override
                        public int compare(BookingSearchListData lhs, BookingSearchListData rhs) {
                            String tempdate1 = lhs.getDate();
                            String[] date1 = tempdate1.split("/");
                            String tempdate2 = rhs.getDate();
                            String[] date2 = tempdate2.split("/");

                            String temptime1 = lhs.getTime();
                            String[] time1 = temptime1.split(":");
                            int result1 = Integer.parseInt(time1[0]);
                            String temptime2 = lhs.getTime();
                            String[] time2 = temptime2.split(":");
                            int result2 = Integer.parseInt(time2[0]);

                            if (date1[2].compareTo(date2[2]) != 0){
                                return date1[2].compareTo(date2[2]);
                            }
                            if (date1[1].compareTo(date2[1]) != 0){
                                return date1[1].compareTo(date2[1]);
                            }
                            if (date1[0].compareTo(date2[0]) != 0){
                                return date1[0].compareTo(date2[0]);
                            }
                            if (result1 > result2){
                                return 1;
                            }
                            return -1;
                        }
                    });

                    customAdapter3 = new RecordListCustomAdapter(SearchActivity.this, temp);
                    list.setAdapter(customAdapter3);
                }
                else {

                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (format == 1){
            ListHospital listHospital = ListHospital.getInstance();

            results1 = new ArrayList<>();
            if (s!=null&&s.length()>0){
                s = s.toString().toUpperCase(Locale.ROOT);

                ArrayList<BookingSearchListHospitalData> filters = new ArrayList<>();
                for(int i = 0; i < listHospital.getHospitalList().size(); i++){
                    String name = listHospital.getHospitalList().get(i).getHospitalName();
                    if(name.toUpperCase(Locale.ROOT).contains(s)){
                        filters.add(new BookingSearchListHospitalData(listHospital.getHospitalList().get(i).getHospitalName(), listHospital.getHospitalList().get(i).getHospitalAddress(),listHospital.getHospitalList().get(i).getIconImage()));
                    }
                }
                results1.addAll(filters);
            }
            else {
                for(int i = 0; i < listHospital.getHospitalList().size(); i++){
                    results1.add(new BookingSearchListHospitalData(listHospital.getHospitalList().get(i).getHospitalName(), listHospital.getHospitalList().get(i).getHospitalAddress(),listHospital.getHospitalList().get(i).getIconImage()));
                }
            }
            customAdapter1.setArrayList(results1);
            list.setAdapter(customAdapter1);
        }
        else if (format == 2){
            if (bookingData.size() != 0){
                UserData userData = UserData.getInstance();
                ArrayList<BookingData> temp = new ArrayList<>();
                temp = userData.getBookingData();

                results2 = new ArrayList<>();

                if (s!=null&&s.length()>0){
                    s = s.toString().toUpperCase(Locale.ROOT);

                    ArrayList<BookingSearchListData> filters = new ArrayList<>();
                    for(int i = 0; i < temp.size(); i++){
                        String hospitalName = temp.get(i).getHospitalName();
                        String serviceName = temp.get(i).getServiceName();
                        String date = temp.get(i).getDate();
                        String time = temp.get(i).getTime();
                        if(hospitalName.toUpperCase(Locale.ROOT).contains(s) || serviceName.toUpperCase(Locale.ROOT).contains(s) || date.toUpperCase(Locale.ROOT).contains(s) || time.toUpperCase(Locale.ROOT).contains(s)){
                            filters.add(new BookingSearchListData(temp.get(i).getOrderID(), temp.get(i).getDate(),temp.get(i).getTime(),temp.get(i).getServiceName(),temp.get(i).getHospitalName(),temp.get(i).getDone()));
                        }
                    }
                    results2.addAll(filters);
                }
                else {
                    for(int i = 0; i < temp.size(); i++){
                        results2.add(new BookingSearchListData(temp.get(i).getOrderID(), temp.get(i).getDate(),temp.get(i).getTime(),temp.get(i).getServiceName(),temp.get(i).getHospitalName(),temp.get(i).getDone()));
                    }
                }

                Collections.sort(results2, new Comparator<BookingSearchListData>() {
                    @Override
                    public int compare(BookingSearchListData lhs, BookingSearchListData rhs) {
                        String tempdate1 = lhs.getDate();
                        String[] date1 = tempdate1.split("/");
                        String tempdate2 = rhs.getDate();
                        String[] date2 = tempdate2.split("/");

                        String temptime1 = lhs.getTime();
                        String[] time1 = temptime1.split(":");
                        int result1 = Integer.parseInt(time1[0]);
                        String temptime2 = lhs.getTime();
                        String[] time2 = temptime2.split(":");
                        int result2 = Integer.parseInt(time2[0]);

                        if (date1[2].compareTo(date2[2]) != 0){
                            return date1[2].compareTo(date2[2]);
                        }
                        if (date1[1].compareTo(date2[1]) != 0){
                            return date1[1].compareTo(date2[1]);
                        }
                        if (date1[0].compareTo(date2[0]) != 0){
                            return date1[0].compareTo(date2[0]);
                        }
                        if (result1 > result2){
                            return 1;
                        }
                        return -1;
                    }
                });
                customAdapter2.setArrayList(results2);
                list.setAdapter(customAdapter2);
            }
        }
        else if (format == 3){
            if (bookingData.size() != 0){
                UserData userData = UserData.getInstance();
                ArrayList<BookingData> temp = new ArrayList<>();
                temp = userData.getBookingData();

                results3 = new ArrayList<>();

                if (s!=null&&s.length()>0){
                    s = s.toString().toUpperCase(Locale.ROOT);

                    ArrayList<BookingSearchListData> filters = new ArrayList<>();
                    for(int i = 0; i < temp.size(); i++){
                        String hospitalName = temp.get(i).getHospitalName();
                        String serviceName = temp.get(i).getServiceName();
                        String date = temp.get(i).getDate();
                        String time = temp.get(i).getTime();
                        if(temp.get(i).getDone() && (hospitalName.toUpperCase(Locale.ROOT).contains(s) || serviceName.toUpperCase(Locale.ROOT).contains(s) || date.toUpperCase(Locale.ROOT).contains(s) || time.toUpperCase(Locale.ROOT).contains(s))){
                            filters.add(new BookingSearchListData(temp.get(i).getOrderID(), temp.get(i).getDate(),temp.get(i).getTime(),temp.get(i).getServiceName(),temp.get(i).getHospitalName(),temp.get(i).getDone()));
                        }
                    }
                    results3.addAll(filters);
                }
                else {
                    for(int i = 0; i < temp.size(); i++){
                        if (temp.get(i).getDone() == Boolean.TRUE){
                            results3.add(new BookingSearchListData(temp.get(i).getOrderID(), temp.get(i).getDate(),temp.get(i).getTime(),temp.get(i).getServiceName(),temp.get(i).getHospitalName(),temp.get(i).getDone()));
                        }
                    }
                }

                Collections.sort(results3, new Comparator<BookingSearchListData>() {
                    @Override
                    public int compare(BookingSearchListData lhs, BookingSearchListData rhs) {
                        String tempdate1 = lhs.getDate();
                        String[] date1 = tempdate1.split("/");
                        String tempdate2 = rhs.getDate();
                        String[] date2 = tempdate2.split("/");

                        String temptime1 = lhs.getTime();
                        String[] time1 = temptime1.split(":");
                        int result1 = Integer.parseInt(time1[0]);
                        String temptime2 = lhs.getTime();
                        String[] time2 = temptime2.split(":");
                        int result2 = Integer.parseInt(time2[0]);

                        if (date1[2].compareTo(date2[2]) != 0){
                            return date1[2].compareTo(date2[2]);
                        }
                        if (date1[1].compareTo(date2[1]) != 0){
                            return date1[1].compareTo(date2[1]);
                        }
                        if (date1[0].compareTo(date2[0]) != 0){
                            return date1[0].compareTo(date2[0]);
                        }
                        if (result1 > result2){
                            return 1;
                        }
                        return -1;
                    }
                });
                customAdapter3.setArrayList(results3);
                list.setAdapter(customAdapter3);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
