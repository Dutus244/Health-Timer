package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;

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
                String phone = inputphone.getText().toString()+"";
                if (phone.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập số điện thoại.", Toast.LENGTH_SHORT).show();
                }
                if (phone.length() != 0 && phone.length() < 9) {
                    Toast.makeText(getApplicationContext(), "Số điện thoại quá ngắn.", Toast.LENGTH_SHORT).show();
                }
                if (phone.length() >= 9){
                    Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity2.class);
                    intent.putExtra("key",phone);
                    startActivity(intent);
                }
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
}
