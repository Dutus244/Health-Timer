package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;

import java.util.Calendar;

public class BookingBookTimeActivity extends AppCompatActivity {
    private String HospitalName;
    private String preService = "";
    private int hour = -1;
    private int minute = -1;
    private int day = 0;
    private int month = 0;
    private int year = 0;
    private Button choose_time_730,choose_time_800,choose_time_830,choose_time_900,choose_time_930,choose_time_1000,choose_time_1330,choose_time_1400,choose_time_1430,choose_time_1500,choose_time_1530,choose_time_1600;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_book_time);

        Bundle extras = getIntent().getExtras();
        HospitalName = extras.getString("name");
        if (extras.getString("preService") != ""){
            preService = extras.getString("preService");
        }
        if (extras.getString("day") != ""){
            day = extras.getInt("day");
        }
        if (extras.getString("month") != ""){
            month = extras.getInt("month");
        }
        if (extras.getString("year") != ""){
            year = extras.getInt("year");
        }

        choose_time_730 = findViewById(R.id.activity_booking_book_time_730);
        choose_time_800 = findViewById(R.id.activity_booking_book_time_800);
        choose_time_830 = findViewById(R.id.activity_booking_book_time_830);
        choose_time_900 = findViewById(R.id.activity_booking_book_time_900);
        choose_time_930 = findViewById(R.id.activity_booking_book_time_930);
        choose_time_1000 = findViewById(R.id.activity_booking_book_time_1000);
        choose_time_1330 = findViewById(R.id.activity_booking_book_time_1330);
        choose_time_1400 = findViewById(R.id.activity_booking_book_time_1400);
        choose_time_1430 = findViewById(R.id.activity_booking_book_time_1430);
        choose_time_1500 = findViewById(R.id.activity_booking_book_time_1500);
        choose_time_1530 = findViewById(R.id.activity_booking_book_time_1530);
        choose_time_1600 = findViewById(R.id.activity_booking_book_time_1600);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int yearnow = calendar.get(Calendar.YEAR);
        int monthnow  = calendar.get(Calendar.MONTH) + 1;
        int daynow = calendar.get(Calendar.DAY_OF_MONTH);
        if (daynow == day && monthnow == month && yearnow == year){
            int hour24hrs = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            if ((hour24hrs == 16 && minutes > 0) || hour24hrs > 16){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_900.setTextColor(getColor(R.color.white));
                choose_time_900.setClickable(false);

                choose_time_930.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_930.setTextColor(getColor(R.color.white));
                choose_time_930.setClickable(false);

                choose_time_1000.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1000.setTextColor(getColor(R.color.white));
                choose_time_1000.setClickable(false);

                choose_time_1330.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1330.setTextColor(getColor(R.color.white));
                choose_time_1330.setClickable(false);

                choose_time_1400.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1400.setTextColor(getColor(R.color.white));
                choose_time_1400.setClickable(false);

                choose_time_1430.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1430.setTextColor(getColor(R.color.white));
                choose_time_1430.setClickable(false);

                choose_time_1500.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1500.setTextColor(getColor(R.color.white));
                choose_time_1500.setClickable(false);

                choose_time_1530.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1530.setTextColor(getColor(R.color.white));
                choose_time_1530.setClickable(false);

                choose_time_1600.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1600.setTextColor(getColor(R.color.white));
                choose_time_1600.setClickable(false);
            }
            else if ((hour24hrs == 15 && minutes > 30) || hour24hrs > 15){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_900.setTextColor(getColor(R.color.white));
                choose_time_900.setClickable(false);

                choose_time_930.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_930.setTextColor(getColor(R.color.white));
                choose_time_930.setClickable(false);

                choose_time_1000.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1000.setTextColor(getColor(R.color.white));
                choose_time_1000.setClickable(false);

                choose_time_1330.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1330.setTextColor(getColor(R.color.white));
                choose_time_1330.setClickable(false);

                choose_time_1400.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1400.setTextColor(getColor(R.color.white));
                choose_time_1400.setClickable(false);

                choose_time_1430.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1430.setTextColor(getColor(R.color.white));
                choose_time_1430.setClickable(false);

                choose_time_1500.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1500.setTextColor(getColor(R.color.white));
                choose_time_1500.setClickable(false);

                choose_time_1530.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1530.setTextColor(getColor(R.color.white));
                choose_time_1530.setClickable(false);

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 15 && minutes > 0) || hour24hrs > 15){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_900.setTextColor(getColor(R.color.white));
                choose_time_900.setClickable(false);

                choose_time_930.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_930.setTextColor(getColor(R.color.white));
                choose_time_930.setClickable(false);

                choose_time_1000.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1000.setTextColor(getColor(R.color.white));
                choose_time_1000.setClickable(false);

                choose_time_1330.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1330.setTextColor(getColor(R.color.white));
                choose_time_1330.setClickable(false);

                choose_time_1400.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1400.setTextColor(getColor(R.color.white));
                choose_time_1400.setClickable(false);

                choose_time_1430.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1430.setTextColor(getColor(R.color.white));
                choose_time_1430.setClickable(false);

                choose_time_1500.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1500.setTextColor(getColor(R.color.white));
                choose_time_1500.setClickable(false);

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 14 && minutes > 30) || hour24hrs > 14){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_900.setTextColor(getColor(R.color.white));
                choose_time_900.setClickable(false);

                choose_time_930.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_930.setTextColor(getColor(R.color.white));
                choose_time_930.setClickable(false);

                choose_time_1000.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1000.setTextColor(getColor(R.color.white));
                choose_time_1000.setClickable(false);

                choose_time_1330.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1330.setTextColor(getColor(R.color.white));
                choose_time_1330.setClickable(false);

                choose_time_1400.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1400.setTextColor(getColor(R.color.white));
                choose_time_1400.setClickable(false);

                choose_time_1430.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1430.setTextColor(getColor(R.color.white));
                choose_time_1430.setClickable(false);

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 14 && minutes > 0) || hour24hrs > 14){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_900.setTextColor(getColor(R.color.white));
                choose_time_900.setClickable(false);

                choose_time_930.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_930.setTextColor(getColor(R.color.white));
                choose_time_930.setClickable(false);

                choose_time_1000.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1000.setTextColor(getColor(R.color.white));
                choose_time_1000.setClickable(false);

                choose_time_1330.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1330.setTextColor(getColor(R.color.white));
                choose_time_1330.setClickable(false);

                choose_time_1400.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1400.setTextColor(getColor(R.color.white));
                choose_time_1400.setClickable(false);

                choose_time_1430.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 13 && minutes > 30) || hour24hrs > 13){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_900.setTextColor(getColor(R.color.white));
                choose_time_900.setClickable(false);

                choose_time_930.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_930.setTextColor(getColor(R.color.white));
                choose_time_930.setClickable(false);

                choose_time_1000.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1000.setTextColor(getColor(R.color.white));
                choose_time_1000.setClickable(false);

                choose_time_1330.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1330.setTextColor(getColor(R.color.white));
                choose_time_1330.setClickable(false);

                choose_time_1400.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1430.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 10 && minutes > 0) || hour24hrs > 10){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_900.setTextColor(getColor(R.color.white));
                choose_time_900.setClickable(false);

                choose_time_930.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_930.setTextColor(getColor(R.color.white));
                choose_time_930.setClickable(false);

                choose_time_1000.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1000.setTextColor(getColor(R.color.white));
                choose_time_1000.setClickable(false);

                choose_time_1330.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",13);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1400.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1430.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 9 && minutes > 30) || hour24hrs > 9){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_900.setTextColor(getColor(R.color.white));
                choose_time_900.setClickable(false);

                choose_time_930.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_930.setTextColor(getColor(R.color.white));
                choose_time_930.setClickable(false);

                choose_time_1000.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_1000.setTextColor(getColor(R.color.white));
                choose_time_1000.setClickable(false);

                choose_time_1330.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",13);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1400.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1430.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 9 && minutes > 30) || hour24hrs > 9){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_900.setTextColor(getColor(R.color.white));
                choose_time_900.setClickable(false);

                choose_time_930.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_930.setTextColor(getColor(R.color.white));
                choose_time_930.setClickable(false);

                choose_time_1000.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",10);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1330.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",13);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1400.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1430.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 9 && minutes > 0) || hour24hrs > 9){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_900.setTextColor(getColor(R.color.white));
                choose_time_900.setClickable(false);

                choose_time_930.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",9);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1000.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",10);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1330.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",13);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1400.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1430.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 8 && minutes > 30) || hour24hrs > 8){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_830.setTextColor(getColor(R.color.white));
                choose_time_830.setClickable(false);

                choose_time_900.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",9);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_930.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",9);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1000.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",10);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1330.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",13);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1400.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1430.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 8 && minutes > 0) || hour24hrs > 8){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_800.setTextColor(getColor(R.color.white));
                choose_time_800.setClickable(false);

                choose_time_830.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",8);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_900.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",9);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_930.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",9);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1000.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",10);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1330.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",13);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1400.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1430.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 8 && minutes > 0) || hour24hrs > 8){
                choose_time_730.setBackground(getDrawable(R.drawable.booking_book_time_hide));
                choose_time_730.setTextColor(getColor(R.color.white));
                choose_time_730.setClickable(false);

                choose_time_800.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",8);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_830.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",8);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_900.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",9);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_930.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",9);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1000.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",10);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1330.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",13);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1400.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1430.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
            else if ((hour24hrs == 7 && minutes > 30) || hour24hrs > 7){
                choose_time_730.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",7);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_800.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",8);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_830.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",8);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_900.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",9);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_930.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",9);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1000.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",10);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1330.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",13);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1400.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1430.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",14);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1500.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });

                choose_time_1530.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",15);
                        intent.putExtra("minute",30);
                        startActivity(intent);
                    }
                });

                choose_time_1600.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                        intent.putExtra("name",HospitalName);
                        intent.putExtra("preService",preService);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month);
                        intent.putExtra("year",year);
                        intent.putExtra("hour",16);
                        intent.putExtra("minute",0);
                        startActivity(intent);
                    }
                });
            }
        }
        else {
            choose_time_730.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",7);
                    intent.putExtra("minute",30);
                    startActivity(intent);
                }
            });

            choose_time_800.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",8);
                    intent.putExtra("minute",0);
                    startActivity(intent);
                }
            });

            choose_time_830.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",8);
                    intent.putExtra("minute",30);
                    startActivity(intent);
                }
            });

            choose_time_900.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",9);
                    intent.putExtra("minute",0);
                    startActivity(intent);
                }
            });

            choose_time_930.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",9);
                    intent.putExtra("minute",30);
                    startActivity(intent);
                }
            });

            choose_time_1000.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",10);
                    intent.putExtra("minute",0);
                    startActivity(intent);
                }
            });

            choose_time_1330.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",13);
                    intent.putExtra("minute",30);
                    startActivity(intent);
                }
            });

            choose_time_1400.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",14);
                    intent.putExtra("minute",0);
                    startActivity(intent);
                }
            });

            choose_time_1430.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",14);
                    intent.putExtra("minute",30);
                    startActivity(intent);
                }
            });

            choose_time_1500.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",15);
                    intent.putExtra("minute",0);
                    startActivity(intent);
                }
            });

            choose_time_1530.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",15);
                    intent.putExtra("minute",30);
                    startActivity(intent);
                }
            });

            choose_time_1600.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                    intent.putExtra("name",HospitalName);
                    intent.putExtra("preService",preService);
                    intent.putExtra("day",day);
                    intent.putExtra("month",month);
                    intent.putExtra("year",year);
                    intent.putExtra("hour",16);
                    intent.putExtra("minute",0);
                    startActivity(intent);
                }
            });
        }
    }
}
