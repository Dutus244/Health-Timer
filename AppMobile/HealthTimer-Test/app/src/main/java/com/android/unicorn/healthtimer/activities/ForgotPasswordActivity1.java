package com.android.unicorn.healthtimer.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordActivity1 extends AppCompatActivity {
    private EditText inputphone;
    private Button forgotpassword,login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_1);


        inputphone = findViewById(R.id.activity_forgot_password_1_input_phone);
        forgotpassword = findViewById(R.id.activity_forgot_password_1_button_forgot_password);
        login = findViewById(R.id.activity_forgot_password_1_button_login);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPassword(v);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ForgotPassword(View view) {
        boolean networkOK = this.checkInternetConnection();
        if (!networkOK) {
            return;
        }

        String phone = inputphone.getText().toString()+"";
        if (phone.length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập số điện thoại.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.length() < 9) {
            Toast.makeText(getApplicationContext(), "Số điện thoại quá ngắn.", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            String URL = getString(R.string.URLServer) + "/Paitent/account/check?id=" + phone;
            RequestQueue queue = Volley.newRequestQueue(ForgotPasswordActivity1.this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String result = response.getString("code");
                        if (result.equals("Available")){
                            Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity2.class);
                            intent.putExtra("key",phone);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Số điện thoại hoặc này chưa được đăng ký", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Không có mạng mặc định nào hiện đang hoạt động", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Mạng không được kết nối", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Mạng không khả dụng", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
