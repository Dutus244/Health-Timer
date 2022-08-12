package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.viewmodels.UserData;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class AccountActivity extends AppCompatActivity {
    private ImageView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        UserData userData = UserData.getInstance();
        String phone = userData.getPhone();
        TextView textphone = findViewById(R.id.activity_account_phone);
        textphone.setText(phone);

        Button buttonChangeInformation = findViewById(R.id.activity_account_change_information);
        buttonChangeInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, ChangeInformationActivity.class);
                startActivity(intent);
            }
        });

        Button buttonChangePassword = findViewById(R.id.activity_account_change_password);
        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        Button buttonLogout = findViewById(R.id.activity_account_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button buttonForApplication = findViewById(R.id.activity_account_for_application);
        buttonForApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, ForApplicationActivity.class);
                startActivity(intent);
            }
        });
    }
}
