package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.UrlEncodedFormEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.BasicHttpParams;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {
    private Button login, register, forgotpassword;
    private EditText inputphone, inputpassword;

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

                final String username = inputphone.getText().toString()+"";
                final String password = inputpassword.getText().toString()+"";

                try {
                    if(username.length()>0 && password.length()>0) {
                        // Gửi username lên server check password
                        Boolean check = Boolean.TRUE;
                        String urlString = "http://ec2-34-220-182-12.us-west-2.compute.amazonaws.com:443/Doc/account/log?id=20127376.TNT&password=272833567";
                        OutputStream out = null;
                        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("Your_var_1", username));
                        nameValuePairs.add(new BasicNameValuePair("Your_var_2", password));
                        try {
                            HttpClient httpclient = new DefaultHttpClient();
                            HttpPost httppost = new HttpPost(urlString);
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                            BasicHttpParams httpParameters = new BasicHttpParams();
                            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
                            HttpResponse response = httpclient.execute(httppost);
                            HttpEntity entity = response.getEntity();
                            InputStream is = entity.getContent();
                            Toast.makeText(getApplicationContext(), "Bạn nhập thiếu số điện thoại hoặc mật khẩu.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        if (check) {
                            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                            startActivity(intent);
                        }
                        else {

                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Bạn nhập thiếu số điện thoại hoặc mật khẩu.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
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
}
