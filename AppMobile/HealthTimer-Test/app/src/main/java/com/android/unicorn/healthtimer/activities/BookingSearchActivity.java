package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.fragments.BookingSearchListHospitalCustomAdapter;
import com.android.unicorn.healthtimer.fragments.BookingSearchListHospitalData;
import com.android.unicorn.healthtimer.viewmodels.ListHospital;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Locale;

public class BookingSearchActivity extends AppCompatActivity implements TextWatcher {
    ListView list_hospital;
    EditText search;
    private BookingSearchListHospitalCustomAdapter customAdapter;

    private ArrayList<BookingSearchListHospitalData> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_search);
        search = findViewById(R.id.activity_booking_search_search);
        search.addTextChangedListener(this);

        list_hospital = findViewById(R.id.activity_booking_search_list_hospital);

        ListHospital listHospital_BookingSearch = ListHospital.getInstance();
        ArrayList<BookingSearchListHospitalData> temp = new ArrayList<>();
        for(int i = 0; i < listHospital_BookingSearch.getHospitalList().size(); i++){
            temp.add(new BookingSearchListHospitalData(listHospital_BookingSearch.getHospitalList().get(i).getHospitalName(), listHospital_BookingSearch.getHospitalList().get(i).getHospitalAddress(),listHospital_BookingSearch.getHospitalList().get(i).getIconImage()));
        }

        customAdapter = new BookingSearchListHospitalCustomAdapter(BookingSearchActivity.this, temp);
        list_hospital.setAdapter(customAdapter);



    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        ListHospital listHospital = ListHospital.getInstance();

        results = new ArrayList<>();
        if (s!=null&&s.length()>0){
            s = s.toString().toUpperCase(Locale.ROOT);

            ArrayList<BookingSearchListHospitalData> filters = new ArrayList<>();
            for(int i = 0; i < listHospital.getHospitalList().size(); i++){
                String name = listHospital.getHospitalList().get(i).getHospitalName();
                if(name.toUpperCase(Locale.ROOT).contains(s)){
                    filters.add(new BookingSearchListHospitalData(listHospital.getHospitalList().get(i).getHospitalName(), listHospital.getHospitalList().get(i).getHospitalAddress(),listHospital.getHospitalList().get(i).getIconImage()));
                }
            }
            results.addAll(filters);
        }
        else {
            for(int i = 0; i < listHospital.getHospitalList().size(); i++){
                results.add(new BookingSearchListHospitalData(listHospital.getHospitalList().get(i).getHospitalName(), listHospital.getHospitalList().get(i).getHospitalAddress(),listHospital.getHospitalList().get(i).getIconImage()));
            }
        }
        customAdapter.setArrayList(results);
        list_hospital.setAdapter(customAdapter);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}