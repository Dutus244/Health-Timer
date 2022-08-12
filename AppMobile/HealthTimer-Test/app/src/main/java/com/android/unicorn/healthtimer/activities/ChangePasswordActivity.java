package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText inputpassword, reinputpassword;
    private Button changepassword;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        UserData userData = UserData.getInstance();
        String phone = userData.getPhone();

        inputpassword = findViewById(R.id.activity_change_password_input_password);
        reinputpassword = findViewById(R.id.activity_change_password_reinput_password);
        changepassword = findViewById(R.id.activity_change_password_button_change_password);

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = inputpassword.getText().toString() + "";
                String repassword = reinputpassword.getText().toString() + "";
                if (password.length() == 0 || repassword.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Bạn phải nhập mật khẩu để tiếp tục.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(repassword)) {
                    Toast.makeText(getApplicationContext(), "2 mật khẩu không trùng nhau.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    try {
                        String URL = getString(R.string.URLServer) + "/Paitent/account/change?id=" + phone + "&pass=" + password;
                        RequestQueue queue = Volley.newRequestQueue(ChangePasswordActivity.this);
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String result = response.getString("code");
                                    if (result.equals("success")){
                                        Toast.makeText(getApplicationContext(), "Mật khẩu của bạn đã được đổi thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Lỗi trong việc đổi mật khẩu", Toast.LENGTH_SHORT).show();
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
}
