package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.viewmodels.BookingData;
import com.android.unicorn.healthtimer.viewmodels.HospitalService;
import com.android.unicorn.healthtimer.viewmodels.ListHospital;
import com.android.unicorn.healthtimer.viewmodels.UserData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookingConfirmActivity extends AppCompatActivity {

    private Button seeSchedule, returnHomePage;
    private String Service = "";
    private String HospitalName = "";
    private String date = "";
    private int hour = -1;
    private int minute = -1;
    private TextView hospitalname, service, Date, time, nameshow, phoneshow;
    private String phone = "";
    private String name = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        Bundle extras = getIntent().getExtras();
        HospitalName = extras.getString("hospitalname");
        Service = extras.getString("service");
        date = extras.getString("date");
        hour = extras.getInt("hour");
        minute = extras.getInt("minute");

        UserData userData = UserData.getInstance();
        phone = userData.getPhone();
        name = userData.getUsername();

        nameshow = findViewById(R.id.activity_booking_confirm_name);
        nameshow.setText(name);
        phoneshow = findViewById(R.id.activity_booking_confirm_phone);
        phoneshow.setText(phone);

        hospitalname = findViewById(R.id.activity_booking_confirm_hospital_name);
        hospitalname.setText(HospitalName);
        service = findViewById(R.id.activity_booking_confirm_service);
        service.setText(Service);
        Date = findViewById(R.id.activity_booking_confirm_date);
        Date.setText(date);
        time = findViewById(R.id.activity_booking_confirm_time);
        if (hour == 7 && minute == 30){
            time.setText("7:30 - 8:00");
        }
        if (hour == 8 && minute == 0){
            time.setText("8:00 - 8:30");
        }
        if (hour == 8 && minute == 30){
            time.setText("8:30 - 9:00");
        }
        if (hour == 9 && minute == 0){
            time.setText("9:00 - 9:30");
        }
        if (hour == 9 && minute == 30){
            time.setText("9:30 - 10:00");
        }
        if (hour == 10 && minute == 0){
            time.setText("10:00 - 10:30");
        }
        if (hour == 13 && minute == 30){
            time.setText("13:30 - 14:00");
        }
        if (hour == 14 && minute == 0){
            time.setText("14:00 - 14:30");
        }
        if (hour == 14 && minute == 30){
            time.setText("14:30 - 15:00");
        }
        if (hour == 15 && minute == 0){
            time.setText("15:00 - 15:30");
        }
        if (hour == 15 && minute == 30){
            time.setText("15:30 - 16:00");
        }
        if (hour == 16 && minute == 0){
            time.setText("16:00 - 16:30");
        }

        String auth = userData.getAuth();

        String URL = getString(R.string.URLServer) + "/Paitent/scheduler?auth=" + auth;
        ArrayList<BookingData> bookingData = new ArrayList<>();
        try {
            RequestQueue queue = Volley.newRequestQueue(BookingConfirmActivity.this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String result = response.getString("code");
                        if (result.equals("success")) {

                            JSONArray jsonArray = response.getJSONArray("data");
                            ListHospital listHospital = ListHospital.getInstance();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject datainside = jsonArray.getJSONObject(i);
                                String a_time = datainside.getString("a_Time");
                                String[] separated = a_time.split(" ");
                                String date = separated[0];
                                String[] separated1 = date.split("-");
                                date = separated1[2] + "/" + separated1[1] + "/" + separated1[0];
                                String time = separated[1];
                                if (time.equals("07:30:00")) {
                                    time = "07:30:00 - 08:00:00";
                                }
                                if (time.equals("08:00:00")) {
                                    time = "08:00:00 - 08:30:00";
                                }
                                if (time.equals("08:30:00")) {
                                    time = "08:30:00 - 09:00:00";
                                }
                                if (time.equals("09:00:00")) {
                                    time = "09:00:00 - 09:30:00";
                                }
                                if (time.equals("09:30:00")) {
                                    time = "09:30:00 - 10:00:00";
                                }
                                if (time.equals("10:00:00")) {
                                    time = "10:00:00 - 10:30:00";
                                }
                                if (time.equals("13:30:00")) {
                                    time = "13:30:00 - 14:00:00";
                                }
                                if (time.equals("14:00:00")) {
                                    time = "14:00:00 - 14:30:00";
                                }
                                if (time.equals("14:30:00")) {
                                    time = "14:30:00 - 15:00:00";
                                }
                                if (time.equals("15:00:00")) {
                                    time = "15:00:00 - 15:30:00";
                                }
                                if (time.equals("15:30:00")) {
                                    time = "15:30:00 - 16:00:00";
                                }
                                if (time.equals("16:00:00")) {
                                    time = "16:00:00 - 16:30:00";
                                }
                                String hospitalname = datainside.getString("hosID");
                                ArrayList<HospitalService> temp = new ArrayList<>();
                                for (int j = 0; j < listHospital.getHospitalList().size(); j++) {
                                    if (listHospital.getHospitalList().get(j).getHospitalID().equals(hospitalname)) {
                                        hospitalname = listHospital.getHospitalList().get(j).getHospitalName();
                                        temp = listHospital.getHospitalList().get(j).getHospitalService();
                                    }
                                }
                                String servicename = datainside.getString("serviceID");
                                for (int j = 0; j < temp.size(); j++) {
                                    if (temp.get(j).getServiceID().equals(servicename)) {
                                        servicename = temp.get(j).getServiceName();
                                    }
                                }
                                String tempisdone = datainside.getString("isDone");
                                Boolean isdone = Boolean.TRUE;
                                if (tempisdone.equals("0")) {
                                    isdone = Boolean.FALSE;
                                }
                                String orderID = datainside.getString("orderID");
                                bookingData.add(new BookingData(date, time, servicename, hospitalname, isdone,orderID));
                            }


                            userData.setBookingData(bookingData);
                        } else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(jsonObjectRequest);
        }
        catch (Exception e){

        }


        seeSchedule = findViewById(R.id.activity_booking_cofirm_see_schedule);
        seeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookingScheduleListActivity.class);
                startActivity(intent);
            }
        });

        returnHomePage = findViewById(R.id.activity_booking_confirm_return_homepage);
        returnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                startActivity(intent);
            }
        });
    }
}
