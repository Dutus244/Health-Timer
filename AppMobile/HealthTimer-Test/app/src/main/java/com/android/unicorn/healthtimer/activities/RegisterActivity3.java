package com.android.unicorn.healthtimer.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.viewmodels.UserData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity3 extends AppCompatActivity {
    private EditText inputpassword, reinputpassword;
    private Button register;
    private String phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_3);

        Bundle extras = getIntent().getExtras();
        phone = extras.getString("key");

        inputpassword = findViewById(R.id.activity_register_3_input_password);
        reinputpassword = findViewById(R.id.activity_register_3_reinput_password);
        register = findViewById(R.id.activity_register_3_button_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = inputpassword.getText().toString()+"";
                String repassword = reinputpassword.getText().toString()+"";
                if (password.length() == 0 || repassword.length() == 0){
                    Toast.makeText(getApplicationContext(), "Bạn phải nhập mật khẩu để tiếp tục.", Toast.LENGTH_SHORT).show();
                }
                if (!password.equals(repassword)){
                    Toast.makeText(getApplicationContext(), "2 mật khẩu không trùng nhau.", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        String URL = getString(R.string.URLServer) + "/Paitent/account/create?id=" + phone + "&password=" + password;
                        RequestQueue queue = Volley.newRequestQueue(RegisterActivity3.this);
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String result = response.getString("code");
                                    if (result.equals("success")){
                                        String URL1 = getString(R.string.URLServer) + "/Paitent/account/log?id=" + phone + "&password=" + password;
                                        RequestQueue queue1 = Volley.newRequestQueue(RegisterActivity3.this);
                                        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, URL1, null, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                try {
                                                    String result1 = response.getString("code");
                                                    if (result1.equals("success")){
                                                        String auth = response.getString("auth");

                                                        UserData userData = UserData.getInstance();
                                                        userData.setPhone(phone);
                                                        userData.setAuth(auth);

                                                        Toast.makeText(getApplicationContext(), "Tài khoản của bạn đã được đăng ký thành công", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                                                        startActivity(intent);
                                                        finish();
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
                                        queue1.add(jsonObjectRequest1);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Đăng ký tài khoản không thành công", Toast.LENGTH_SHORT).show();
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
            }
        });

    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity1.class);
                    startActivity(intent);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc quay trở lại trong bước này?").setPositiveButton("Có", dialogClickListener).setNegativeButton("Không", dialogClickListener).show();
        return;
    }

}
