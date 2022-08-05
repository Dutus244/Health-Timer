package com.android.unicorn.healthtimer.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.viewmodels.HospitalData;
import com.android.unicorn.healthtimer.viewmodels.HospitalService;
import com.android.unicorn.healthtimer.viewmodels.ListHospital;
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

public class ForeplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreplay);
        ListHospital listHospital_Homepage = ListHospital.getInstance();
        String URL = getString(R.string.URLServer) + "/Hos/all";
        ArrayList<HospitalData> ListHospitals = new ArrayList<>();
        try {
            RequestQueue queue = Volley.newRequestQueue(ForeplayActivity.this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String result = response.getString("code");
                        if (result.equals("success")){
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject datainside = jsonArray.getJSONObject(i);
                                String ID = datainside.getString("ID");
                                String name = datainside.getString("name");
                                String shortname = datainside.getString("shortname");
                                String describe = datainside.getString("describe");
                                String isOn = datainside.getString("isOn");
                                Boolean ison;
                                if (Integer.parseInt(isOn) == 1){
                                    ison = Boolean.TRUE;
                                }
                                else {
                                    ison = Boolean.FALSE;
                                }
                                String address = datainside.getString("address");

                                String img = "";
                                String temp1 = datainside.getString("img");
                                if (temp1 != ""){
                                    String[] separated1 = temp1.split("/");
                                    img = getString(R.string.URLServer) + "/" + separated1[0] + "/" + separated1[1];
                                }

                                String icon_img = "";
                                String temp2 = datainside.getString("icon_img");
                                if (temp2 != ""){
                                    String[] separated2 = temp2.split("/");
                                    icon_img = getString(R.string.URLServer) + "/" + separated2[0] + "/" + separated2[1];
                                }
                                ArrayList<HospitalService> hospitalServices = new ArrayList<>();
                                String URL = getString(R.string.URLServer) + "/Service?id=" + ID;
                                try {
                                    RequestQueue queue = Volley.newRequestQueue(ForeplayActivity.this);
                                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                String result = response.getString("code");
                                                if (result.equals("success")){
                                                    JSONArray jsonArray = response.getJSONArray("data");
                                                    for (int i = 0; i < jsonArray.length(); i++) {
                                                        JSONObject datainside = jsonArray.getJSONObject(i);
                                                        String serviceName = datainside.getString("serviceName");
                                                        String serviceID = datainside.getString("serviceID");
                                                        hospitalServices.add(new HospitalService(serviceName,serviceID));
                                                    }
                                                }
                                                else {

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
                                    SystemClock.sleep(100);
                                }
                                catch (Exception e){

                                }

                                ListHospitals.add(new HospitalData(ID,name,shortname,address,describe,ison,icon_img,img,hospitalServices));
                            }
                            listHospital_Homepage.setHospitalList(ListHospitals);
                        }
                        else {

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



        ActivityCompat.requestPermissions(ForeplayActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
