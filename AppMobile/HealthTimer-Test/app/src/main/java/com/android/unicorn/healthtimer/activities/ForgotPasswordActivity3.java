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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordActivity3 extends AppCompatActivity {
    private EditText inputpassword, reinputpassword;
    private Button forgotpassword;
    private String phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_3);

        Bundle extras = getIntent().getExtras();
        phone = extras.getString("key");

        inputpassword = findViewById(R.id.activity_forgot_password_3_input_password);
        reinputpassword = findViewById(R.id.activity_forgot_password_3_reinput_password);
        forgotpassword = findViewById(R.id.activity_forgot_password_3_button_forgot_password);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
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
                        RequestQueue queue = Volley.newRequestQueue(ForgotPasswordActivity3.this);
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String result = response.getString("code");
                                    if (result.equals("success")){
                                        Toast.makeText(getApplicationContext(), "Mật khẩu của bạn đã được đổi thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        intent.putExtra("key",phone);
                                        startActivity(intent);
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

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity1.class);
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
