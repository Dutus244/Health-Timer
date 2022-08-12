package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

import java.util.Calendar;

public class ChangeInformationActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserData userData = UserData.getInstance();
        String auth = userData.getAuth();

        setContentView(R.layout.activity_change_information);
        EditText inputfullname = findViewById(R.id.activity_change_information_input_fullname);
        if (userData.getFullname() != null || userData.getFullname().equals("")){
            inputfullname.setText(userData.getFullname());
        }
        EditText inputcitizenID = findViewById(R.id.activity_change_information_input_citizenID);
        if (userData.getCitizenID() != null || userData.getCitizenID().equals("")){
            inputcitizenID.setText(userData.getCitizenID());
        }
        EditText inputbirthday = findViewById(R.id.activity_change_information_input_birthday);
        String tempbirth = userData.getBirthday();
        if (tempbirth != null || tempbirth != ""){
            String[] temp = tempbirth.split("-");
            String birth = temp[0] + "/" + temp[1] + "/" + temp[2];
            inputbirthday.setText(birth);
        }
        EditText inputaddress = findViewById(R.id.activity_change_information_input_address);
        if (userData.getAddress() != null || userData.getAddress().equals("")){
            inputaddress.setText(userData.getAddress());
        }
        CheckBox checkBox = findViewById(R.id.activity_change_information_checkbox_rules);
        Button buttonConfirm = findViewById(R.id.activity_change_information_button_confirm);

        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon - 1);
                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    inputbirthday.setText(current);
                    inputbirthday.setSelection(sel < current.length() ? sel : current.length());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        inputbirthday.addTextChangedListener(tw);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname = inputfullname.getText().toString()+"";
                final String citizenID = inputcitizenID.getText().toString()+"";
                final String birthday = inputbirthday.getText().toString()+"";
                final String address = inputaddress.getText().toString()+"";
                if (fullname.length() == 0 || citizenID.length() == 0 || birthday.length() == 0 || address.length() == 0){
                    Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ các thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!checkBox.isChecked()){
                    Toast.makeText(getApplicationContext(), "Bạn chưa xác thực về độ tin cậy của thông tin.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] temp = birthday.split("/");
                String birth = temp[0] + "/" + temp[1] + "/" + temp[2];
                String URL = getString(R.string.URLServer) + "/Paitent/Edit?auth=" + auth + "&name=" + fullname + "&citizenID=" + citizenID + "&addr=" + address + "&bthday=" + birth;
                RequestQueue queue = Volley.newRequestQueue(ChangeInformationActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = response.getString("code");
                            if (result.equals("success")) {
                                userData.setCitizenID(citizenID);
                                userData.setFullname(fullname);
                                userData.setBirthday(birth);
                                userData.setAddress(address);
                                Intent intent_booking = new Intent(getApplicationContext(), BookingSearchActivity.class);
                                startActivity(intent_booking);
                                finish();
                            } else {

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
        });
    }
}