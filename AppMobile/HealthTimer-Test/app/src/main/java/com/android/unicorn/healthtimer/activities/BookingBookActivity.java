package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.fragments.BookingSearchListHospitalData;
import com.android.unicorn.healthtimer.viewmodels.BookingData;
import com.android.unicorn.healthtimer.viewmodels.HospitalData;
import com.android.unicorn.healthtimer.viewmodels.HospitalService;
import com.android.unicorn.healthtimer.viewmodels.ListHospital;
import com.android.unicorn.healthtimer.viewmodels.UserData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class BookingBookActivity extends AppCompatActivity {
    String HospitalID;
    String HospitalName;
    String HospitalShortName;
    String HospitalAddress;
    String HospitalDescribe;
    boolean HospitalIsOn;
    String IconImage;
    String Image;
    private Button button_choose_date, button_choose_time;
    private String preService = "";
    private String preDate = "";
    private String preTime = "";
    private int day = 0;
    private int month = 0;
    private int year = 0;
    private int hour = -1;
    private int minute = -1;
    int spinnerPosition = 0;
    ArrayList<String> ServiceName,ServiceID;
    private Button button_confirm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_book);

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
        if (extras.getString("hour") != ""){
            hour = extras.getInt("hour");
        }
        if (extras.getString("minute") != ""){
            minute = extras.getInt("minute");
        }
        ArrayList<HospitalService> temp = new ArrayList<>();
        ListHospital listHospital_BookingBook = ListHospital.getInstance();
        for(int i = 0; i < listHospital_BookingBook.getHospitalList().size(); i++){
            if (HospitalName.equals(listHospital_BookingBook.getHospitalList().get(i).getHospitalName())){
                HospitalID = listHospital_BookingBook.getHospitalList().get(i).getHospitalID();
                HospitalShortName = listHospital_BookingBook.getHospitalList().get(i).getHospitalShortName();
                HospitalAddress = listHospital_BookingBook.getHospitalList().get(i).getHospitalAddress();
                HospitalDescribe = listHospital_BookingBook.getHospitalList().get(i).getHospitalDescribe();
                HospitalIsOn = listHospital_BookingBook.getHospitalList().get(i).isHospitalIsOn();
                IconImage = listHospital_BookingBook.getHospitalList().get(i).getIconImage();
                Image = listHospital_BookingBook.getHospitalList().get(i).getImage();
                temp = listHospital_BookingBook.getHospitalList().get(i).getHospitalService();
                break;
            }
        }

        ImageView imageViewHosptalIconImage = findViewById(R.id.activity_booking_book_hospital_icon_image);
        Picasso.with(BookingBookActivity.this).load(IconImage).into(imageViewHosptalIconImage);

        TextView textViewHospitalName = findViewById(R.id.activity_booking_book_hospital_name);
        textViewHospitalName.setText(HospitalName);

        ImageView imageViewHosptalImage = findViewById(R.id.activity_booking_book_hospital_image);
        Picasso.with(BookingBookActivity.this).load(Image).resize(600, 320).into(imageViewHosptalImage);

        TextView textViewHospitalDescribe = findViewById(R.id.activity_booking_book_hospital_describe);
        textViewHospitalDescribe.setText(HospitalDescribe);

        Spinner spinnerService = findViewById(R.id.activity_booking_book_service_spinner);
        ServiceName = new ArrayList<>();
        ServiceID = new ArrayList<>();
        ServiceName.add("Chọn dịch vụ");
        ServiceID.add("fail");
        for (int i = 0; i < temp.size(); i++){
            ServiceName.add(temp.get(i).getServiceName());
            ServiceID.add(temp.get(i).getServiceID());
        }
        ArrayAdapter<String> ServiceNameAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                ServiceName
        );
        spinnerService.setAdapter(ServiceNameAdapter);

        if (preService != ""){
            ArrayAdapter myAdap = (ArrayAdapter) spinnerService.getAdapter();
            spinnerPosition = myAdap.getPosition(preService);
            spinnerService.setSelection(spinnerPosition);
        }

        button_choose_date = findViewById(R.id.activity_booking_book_date_button);
        if (day != 0 && month != 0 && year != 0){
            button_choose_date.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
        }
        button_choose_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookingBookDateActivity.class);
                preService = spinnerService.getSelectedItem().toString();
                intent.putExtra("name",HospitalName);
                intent.putExtra("preService",preService);
                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);
                startActivity(intent);
            }
        });

        button_choose_time = findViewById(R.id.activity_booking_book_time_button);
        if (hour == 7 && minute == 30){
            button_choose_time.setText("7:30 - 8:00");
        }
        if (hour == 8 && minute == 0){
            button_choose_time.setText("8:00 - 8:30");
        }
        if (hour == 8 && minute == 30){
            button_choose_time.setText("8:30 - 9:00");
        }
        if (hour == 9 && minute == 0){
            button_choose_time.setText("9:00 - 9:30");
        }
        if (hour == 9 && minute == 30){
            button_choose_time.setText("9:30 - 10:00");
        }
        if (hour == 10 && minute == 0){
            button_choose_time.setText("10:00 - 10:30");
        }
        if (hour == 13 && minute == 30){
            button_choose_time.setText("13:30 - 14:00");
        }
        if (hour == 14 && minute == 0){
            button_choose_time.setText("14:00 - 14:30");
        }
        if (hour == 14 && minute == 30){
            button_choose_time.setText("14:30 - 15:00");
        }
        if (hour == 15 && minute == 0){
            button_choose_time.setText("15:00 - 15:30");
        }
        if (hour == 15 && minute == 30){
            button_choose_time.setText("15:30 - 16:00");
        }
        if (hour == 16 && minute == 0){
            button_choose_time.setText("16:00 - 16:30");
        }
        button_choose_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(day == 0 && month == 0 && year == 0){
                    Toast.makeText(BookingBookActivity.this, "Hãy chọn ngày khám trước khi chọn giờ khám", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), BookingBookTimeActivity.class);
                preService = spinnerService.getSelectedItem().toString();
                intent.putExtra("name",HospitalName);
                intent.putExtra("preService",preService);
                intent.putExtra("day",day);
                intent.putExtra("month",month);
                intent.putExtra("year",year);
                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);
                startActivity(intent);

                String tempURL = getString(R.string.URLServer) + "/Paitent/appoinment?auth=123456789&time=000000&serviceI;D=0000&hosID=000";
                try {
                    RequestQueue queue = Volley.newRequestQueue(BookingBookActivity.this);
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, tempURL, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

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
            }
        });

        button_confirm = findViewById(R.id.activity_booking_book_confirm_book);
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerPosition = spinnerService.getSelectedItemPosition();
                if (spinnerPosition == 0){
                    Toast.makeText(BookingBookActivity.this, "Hãy chọn dịch vụ trước khi đặt lịch", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (year == 0 || month == 0 || day == 0){
                    Toast.makeText(BookingBookActivity.this, "Hãy chọn ngày trước khi đặt lịch", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (hour == -1 || minute == -1 || hour == 0){
                    Toast.makeText(BookingBookActivity.this, "Hãy chọn giờ trước khi đặt lịch", Toast.LENGTH_SHORT).show();
                    return;
                }

                UserData userData = UserData.getInstance();
                String auth = userData.getAuth();

                String bookyear = String.format("%04d", year);
                String bookmonth = String.format("%02d", month);
                String bookday = String.format("%02d", day);

                String bookhour = String.format("%02d", hour);
                String bookminute = String.format("%02d", minute);
                String time = bookyear + "-" + bookmonth + "-" + bookday + " " + bookhour + ":" + bookminute + ":00";

                String serviceid = ServiceID.get(spinnerPosition);

                String URL = getString(R.string.URLServer) + "/Paitent/appoinment?auth=" + auth + "&time=" + time + "&serviceID=" + serviceid + "&hosID=" + HospitalID;
                try {
                    RequestQueue queue = Volley.newRequestQueue(BookingBookActivity.this);
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String result = response.getString("code");
                                if (result.equals("success")){
                                    Toast.makeText(getApplicationContext(), "Đặt lịch thành công", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), BookingConfirmActivity.class);
                                    String service = spinnerService.getSelectedItem().toString();
                                    intent.putExtra("hospitalname",HospitalName);
                                    intent.putExtra("service",service);
                                    intent.putExtra("date",bookday + "/" + bookmonth + "/" + bookyear);
                                    intent.putExtra("hour",hour);
                                    intent.putExtra("minute",minute);

                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Đặt lịch thất bại", Toast.LENGTH_SHORT).show();
                                    return;
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
                    Toast.makeText(getApplicationContext(), "Bị lỗi kết nối", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
