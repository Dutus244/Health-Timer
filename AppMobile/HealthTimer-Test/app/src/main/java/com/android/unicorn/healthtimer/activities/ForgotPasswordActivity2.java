package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.fragments.GenericTextWatcher_ForgotPassword;

import java.util.Random;

public class ForgotPasswordActivity2 extends AppCompatActivity {
    private EditText otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four;
    private int otp;
    private Button forgot_password,resend_otp,change_phone;
    private String phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_2);

        Bundle extras = getIntent().getExtras();
        phone = extras.getString("key");

        Random r = new Random();
        otp = r.nextInt(9999 - 1000) + 1000;
        SmsManager sm = SmsManager.getDefault();
        sm.sendTextMessage(phone, null, "Mã xác nhận của bạn là " + String.valueOf(otp), null, null);

        otp_textbox_one = findViewById(R.id.activity_forgot_password_2_otp_edit_box1);
        otp_textbox_two = findViewById(R.id.activity_forgot_password_2_otp_edit_box2);
        otp_textbox_three = findViewById(R.id.activity_forgot_password_2_otp_edit_box3);
        otp_textbox_four = findViewById(R.id.activity_forgot_password_2_otp_edit_box4);
        forgot_password = findViewById(R.id.activity_forgot_password_2_button_forgot_password);
        resend_otp = findViewById(R.id.activity_forgot_password_2_button_resend_otp);
        change_phone = findViewById(R.id.activity_forgot_password_2_button_change_phone);


        EditText[] edit = {otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four};

        otp_textbox_one.addTextChangedListener(new GenericTextWatcher_ForgotPassword(otp_textbox_one, edit));
        otp_textbox_two.addTextChangedListener(new GenericTextWatcher_ForgotPassword(otp_textbox_two, edit));
        otp_textbox_three.addTextChangedListener(new GenericTextWatcher_ForgotPassword(otp_textbox_three, edit));
        otp_textbox_four.addTextChangedListener(new GenericTextWatcher_ForgotPassword(otp_textbox_four, edit));

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = Integer.parseInt(edit[0].getText().toString()) * 1000 +  Integer.parseInt(edit[1].getText().toString()) * 100 +  Integer.parseInt(edit[2].getText().toString()) * 10 +  Integer.parseInt(edit[3].getText().toString());
                if (check == otp){
                    Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity3.class);
                    intent.putExtra("key",phone);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Mã OTP của bạn chưa đúng.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = r.nextInt(9999 - 1000) + 1000;
                SmsManager sm = SmsManager.getDefault();
                sm.sendTextMessage(phone, null, "Mã xác nhận của bạn là " + String.valueOf(otp), null, null);
            }
        });

        change_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity1.class);
                startActivity(intent);
            }
        });
    }
}
