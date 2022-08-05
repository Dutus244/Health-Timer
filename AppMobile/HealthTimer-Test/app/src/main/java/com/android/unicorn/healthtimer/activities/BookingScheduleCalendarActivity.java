package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.fragments.BookingSearchListCustomAdapter;
import com.android.unicorn.healthtimer.fragments.BookingSearchListData;
import com.android.unicorn.healthtimer.viewmodels.BookingData;
import com.android.unicorn.healthtimer.viewmodels.UserData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class BookingScheduleCalendarActivity extends AppCompatActivity {
    private Button button_schedule_list;
    private ListView list_booking;
    private BookingSearchListCustomAdapter customAdapter;

    private ArrayList<BookingData> bookingData;
    int count = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_schedule_calendar);

        DatePicker datePicker = findViewById(R.id.activity_booking_schedule_calendar_datePicker);
        button_schedule_list = findViewById(R.id.activity_booking_schedule_calendar_schedule_list);
        button_schedule_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookingScheduleListActivity.class);
                startActivity(intent);
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        UserData userData = UserData.getInstance();
        bookingData = new ArrayList<>();
        bookingData = userData.getBookingData();
        TextView textViewempty = findViewById(R.id.activity_booking_schedule_calendar_empty);
        TextView textView = findViewById(R.id.activity_booking_schedule_calendar_date_show);
        datePicker.init( year, month , day , new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                textView.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
                ArrayList<BookingSearchListData> temp = new ArrayList<>();
                for(int i = 0; i < bookingData.size(); i++){
                    String bookyear = String.format("%04d", year);
                    String bookmonth = String.format("%02d", month + 1);
                    String bookday = String.format("%02d", dayOfMonth);
                    String date = bookday + "/" + bookmonth + "/" + bookyear;
                    if (bookingData.get(i).getDate().equals(date)){
                        temp.add(new BookingSearchListData(bookingData.get(i).getDate(),bookingData.get(i).getTime(),bookingData.get(i).getServiceName(),bookingData.get(i).getHospitalName(),bookingData.get(i).getDone()));
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
                list_booking = findViewById(R.id.activity_booking_schedule_list_list_booking);
                if (count == 0){
                    customAdapter = new BookingSearchListCustomAdapter(BookingScheduleCalendarActivity.this, temp);
                    list_booking.setAdapter(customAdapter);
                }
                else {
                    customAdapter.setArrayList(temp);
                    list_booking.setAdapter(customAdapter);
                }
                if (temp.size() == 0){
                    textViewempty.setText("Bạn không đặt lịch trong ngày này");
                }
                else {
                    textViewempty.setText("");
                }
                count = count + 1;
            }
        });
    }
}
