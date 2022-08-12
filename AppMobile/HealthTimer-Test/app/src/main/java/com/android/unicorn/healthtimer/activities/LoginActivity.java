package com.android.unicorn.healthtimer.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.viewmodels.BookingData;
import com.android.unicorn.healthtimer.viewmodels.HospitalService;
import com.android.unicorn.healthtimer.viewmodels.ListBooking;
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


public class LoginActivity extends AppCompatActivity {
    private Button login, register, forgotpassword;
    private EditText inputphone, inputpassword;
    ListBooking listBooking_homepage = ListBooking.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.activity_login_button_login);
        register = findViewById(R.id.activity_login_button_register);
        forgotpassword = findViewById(R.id.activity_login_button_forgot_password);

        inputphone = findViewById(R.id.activity_login_input_phone);
        inputpassword = findViewById(R.id.activity_login_input_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(v);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity1.class);
                startActivity(intent);
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity1.class);
                startActivity(intent);
            }
        });
    }

    public void Login(View view){

        boolean networkOK = this.checkInternetConnection();
        if (!networkOK) {
            return;
        }

        final String phone = inputphone.getText().toString()+"";
        final String password = inputpassword.getText().toString()+"";

        if (phone.length()==0 || password.length()==0) {
            Toast.makeText(getApplicationContext(), "Bạn nhập thiếu số điện thoại hoặc mật khẩu.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.length() < 9){
            Toast.makeText(getApplicationContext(), "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6){
            Toast.makeText(getApplicationContext(), "Mật khẩu không được ít hơn 6 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }

        String URL = getString(R.string.URLServer) + "/Paitent/account/log?id=" + phone + "&password=" + password;
        try {
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String result = response.getString("code");
                        if (result.equals("success")){
                            String auth = response.getString("auth");

                            UserData userData = UserData.getInstance();
                            userData.setPhone(phone);
                            userData.setAuth(auth);


                            String URL = getString(R.string.URLServer) + "/Paitent/scheduler?auth=" + auth;
                            ArrayList<BookingData> bookingData = new ArrayList<>();
                            try {
                                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            String result = response.getString("code");
                                            if (result.equals("success")){

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
                                                    if (time.equals("07:30:00")){
                                                        time = "07:30:00 - 08:00:00";
                                                    }
                                                    if (time.equals("08:00:00")){
                                                        time = "08:00:00 - 08:30:00";
                                                    }
                                                    if (time.equals("08:30:00")){
                                                        time = "08:30:00 - 09:00:00";
                                                    }
                                                    if (time.equals("09:00:00")){
                                                        time = "09:00:00 - 09:30:00";
                                                    }
                                                    if (time.equals("09:30:00")){
                                                        time = "09:30:00 - 10:00:00";
                                                    }
                                                    if (time.equals("10:00:00")){
                                                        time = "10:00:00 - 10:30:00";
                                                    }
                                                    if (time.equals("13:30:00")){
                                                        time = "13:30:00 - 14:00:00";
                                                    }
                                                    if (time.equals("14:00:00")){
                                                        time = "14:00:00 - 14:30:00";
                                                    }
                                                    if (time.equals("14:30:00")){
                                                        time = "14:30:00 - 15:00:00";
                                                    }
                                                    if (time.equals("15:00:00")){
                                                        time = "15:00:00 - 15:30:00";
                                                    }
                                                    if (time.equals("15:30:00")){
                                                        time = "15:30:00 - 16:00:00";
                                                    }
                                                    if (time.equals("16:00:00")){
                                                        time = "16:00:00 - 16:30:00";
                                                    }
                                                    String hospitalname = datainside.getString("hosID");
                                                    ArrayList<HospitalService> temp = new ArrayList<>();
                                                    for(int j = 0; j < listHospital.getHospitalList().size(); j++){
                                                        if (listHospital.getHospitalList().get(j).getHospitalID().equals(hospitalname)){
                                                            hospitalname = listHospital.getHospitalList().get(j).getHospitalName();
                                                            temp = listHospital.getHospitalList().get(j).getHospitalService();
                                                        }
                                                    }
                                                    String servicename = datainside.getString("serviceID");
                                                    for (int j = 0; j < temp.size(); j++){
                                                        if (temp.get(j).getServiceID().equals(servicename)){
                                                            servicename = temp.get(j).getServiceName();
                                                        }
                                                    }
                                                    String tempisdone = datainside.getString("isDone");
                                                    Boolean isdone = Boolean.TRUE;
                                                    if (tempisdone.equals("0")){
                                                        isdone = Boolean.FALSE;
                                                    }
                                                    String orderID = datainside.getString("orderID");

                                                    bookingData.add(new BookingData(date,time,servicename,hospitalname,isdone,orderID));
                                                }


                                                userData.setBookingData(bookingData);
                                            }
                                            else {

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
                            SystemClock.sleep(100);
                            Toast.makeText(getApplicationContext(), "Bạn đã đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Số điện thoại hoặc mật khẩu của bạn không đúng", Toast.LENGTH_SHORT).show();
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

    public boolean checkInternetConnection() {
        // Get Connectivity Manager
        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Details about the currently active default data network
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "Không có mạng mặc định nào hiện đang hoạt động", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Mạng không được kết nối", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Mạng không khả dụng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }

}
