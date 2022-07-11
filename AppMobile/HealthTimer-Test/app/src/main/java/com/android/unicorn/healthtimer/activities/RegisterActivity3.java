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

public class RegisterActivity3 extends AppCompatActivity {
    private EditText inputpassword, reinputpassword;
    private Button register;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_3);

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
                    Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                    startActivity(intent);
                    finish();
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
