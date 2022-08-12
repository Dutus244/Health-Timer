package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;

import java.time.Year;
import java.util.Calendar;

public class BookingBookDateActivity extends AppCompatActivity {
    private String HospitalName;
    private String preService = "";
    private int hour = -1;
    private int minute = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_book_date);

        Bundle extras = getIntent().getExtras();
        HospitalName = extras.getString("name");
        if (extras.getString("preService") != ""){
            preService = extras.getString("preService");
        }
        if (extras.getString("hour") != ""){
            hour = extras.getInt("hour");
        }
        if (extras.getString("minute") != ""){
            minute = extras.getInt("minute");
        }

        DatePicker datePicker = findViewById(R.id.activity_booking_book_date_datePicker);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        final int[] daychoose = new int[1];
        final int[] monthchoose = new int[1];
        final int[] yearchoose = new int[1];

        TextView textView = findViewById(R.id.activity_booking_book_date_show);
        datePicker.init( year, month , day , new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                textView.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
                daychoose[0] = dayOfMonth;
                monthchoose[0] = month + 1;
                yearchoose[0] = year;
            }
        });

        Button choose_date = findViewById(R.id.activity_booking_book_date_choose_button);
        choose_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookingBookActivity.class);
                intent.putExtra("name",HospitalName);
                intent.putExtra("preService",preService);
                intent.putExtra("day",daychoose[0]);
                intent.putExtra("month",monthchoose[0]);
                intent.putExtra("year",yearchoose[0]);
                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);
                startActivity(intent);
            }
        });
    }
}
