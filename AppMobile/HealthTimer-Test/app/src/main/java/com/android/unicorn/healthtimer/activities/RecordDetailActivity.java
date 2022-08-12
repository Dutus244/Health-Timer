package com.android.unicorn.healthtimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.viewmodels.UserData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecordDetailActivity extends AppCompatActivity {
    ArrayList<String>prescriptionsNameList = new ArrayList<>();
    ArrayList<String>prescriptionsAmountList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        Bundle extras = getIntent().getExtras();

        UserData userData = UserData.getInstance();

        String fullname = userData.getFullname();
        if (fullname != null){
            TextView textfullname = findViewById(R.id.activity_record_detail_user_name);
            textfullname.setText(fullname);
        }

        String citizenID = userData.getCitizenID();
        if (citizenID != null){
            TextView textcitizenID = findViewById(R.id.activity_record_detail_user_citizenID);
            textcitizenID.setText(citizenID);
        }

        String birthday = userData.getBirthday();
        if (birthday != null){
            TextView textbirthday = findViewById(R.id.activity_record_detail_user_birthday);
            textbirthday.setText(birthday);
        }

        String address = userData.getAddress();
        if (address != null){
            TextView textaddress = findViewById(R.id.activity_record_detail_user_address);
            textaddress.setText(address);
        }

        String orderID = extras.getString("orderID");

        String HospitalName = extras.getString("hos_name");
        TextView texthos_name = findViewById(R.id.activity_record_detail_hospital_name);
        texthos_name.setText(HospitalName);

        String service = extras.getString("ser_name");
        TextView textser_name = findViewById(R.id.activity_record_detail_service);
        textser_name.setText(service);

        String symptom = extras.getString("symptom");
        TextView textsymptom = findViewById(R.id.activity_record_detail_user_symptom);
        textsymptom.setText(symptom);

        String diagnose = extras.getString("diagnose");
        TextView textdiagnose = findViewById(R.id.activity_record_detail_user_diagnose);
        textdiagnose.setText(diagnose);

        String docname = extras.getString("doc_name");
        TextView textdocname = findViewById(R.id.activity_record_detail_doc_name);
        textdocname.setText(docname);

        String inputdate = extras.getString("date");
        String[] temp = inputdate.split("/");
        String date = "Ngày " + temp[0] + " tháng " + temp[1] + " năm " + temp[2];
        TextView textday = findViewById(R.id.activity_record_detail_day);
        textday.setText(date);

        TextView textprescription = findViewById(R.id.activity_record_detail_user_prescription);
        textprescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prescriptionsNameList.clear();
                prescriptionsAmountList.clear();

                String URL3 = getString(R.string.URLServer) + "/Paitent/scheduler/getp?orderID=" + orderID;
                RequestQueue queue3 = Volley.newRequestQueue(RecordDetailActivity.this);
                JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.GET, URL3, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = response.getString("code");
                            if (result.equals("success")) {
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject datainside = jsonArray.getJSONObject(i);
                                    prescriptionsNameList.add(datainside.getString("name"));
                                    prescriptionsAmountList.add(datainside.getString("amount"));
                                }
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
                queue3.add(jsonObjectRequest3);

                SystemClock.sleep(1000);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RecordDetailActivity.this, PrescriptionActivity.class);
                        intent.putExtra("hos_name",HospitalName);
                        intent.putExtra("ser_name",service);
                        intent.putExtra("diagnose",diagnose);
                        intent.putExtra("doc_name",docname);
                        intent.putExtra("date",inputdate);
                        intent.putExtra("prescriptionsNameList",prescriptionsNameList);
                        intent.putExtra("prescriptionsAmountList",prescriptionsAmountList);
                        startActivity(intent);
                    }
                }, 500);
            }
        });
    }
}
