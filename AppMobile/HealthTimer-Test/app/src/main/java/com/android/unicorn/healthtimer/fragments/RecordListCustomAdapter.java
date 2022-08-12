package com.android.unicorn.healthtimer.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.activities.BookingBookTimeActivity;
import com.android.unicorn.healthtimer.activities.LoginActivity;
import com.android.unicorn.healthtimer.activities.RecordDetailActivity;
import com.android.unicorn.healthtimer.activities.WelcomeActivity;
import com.android.unicorn.healthtimer.viewmodels.BookingData;
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
import java.util.Calendar;

public class RecordListCustomAdapter extends BaseAdapter {
    private ArrayList<BookingSearchListData> originList;
    private Context context;
    String doctorName = "";
    String diagnose = "";
    String symptom = "";

    public RecordListCustomAdapter(Context context, ArrayList<BookingSearchListData> originList) {
        this.originList=originList;
        this.context=context;
    }

    public ArrayList<BookingSearchListData> getArrayList() {
        return originList;
    }

    public void setArrayList(ArrayList<BookingSearchListData> arrayList) {
        this.originList.clear();
        this.originList.addAll(arrayList);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return originList.size();
    }

    @Override
    public Object getItem(int position) {
        return originList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookingSearchListData bookingSearchListData=originList.get(position);
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.record_list, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String orderID = bookingSearchListData.OrderID;
                    symptom = "";
                    String URL1 = context.getString(R.string.URLServer) + "/Paitent/scheduler/result?orderID=" + orderID;
                    RequestQueue queue1 = Volley.newRequestQueue(context);
                    JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, URL1, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String result = response.getString("code");
                                if (result.equals("success")) {
                                    JSONArray jsonArray = response.getJSONArray("data");
                                    JSONObject datainside = jsonArray.getJSONObject(0);
                                    doctorName = datainside.getString("doc");
                                    diagnose = datainside.getString("result");
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
                    queue1.add(jsonObjectRequest1);

                    SystemClock.sleep(1000);
                    String URL2 = context.getString(R.string.URLServer) + "/Paitent/scheduler/detailget?orderID=" + orderID;
                    RequestQueue queue2 = Volley.newRequestQueue(context);
                    JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, URL2, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String result = response.getString("code");
                                if (result.equals("success")) {
                                    JSONArray jsonArray = response.getJSONArray("data");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject datainside = jsonArray.getJSONObject(i);
                                        symptom = symptom + datainside.getString("name") + " - " + datainside.getString("value") + System.getProperty ("line.separator");
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
                    queue2.add(jsonObjectRequest2);

                    SystemClock.sleep(500);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(context, RecordDetailActivity.class);
                            intent.putExtra("orderID",bookingSearchListData.OrderID);
                            intent.putExtra("hos_name",bookingSearchListData.HospitalName);
                            intent.putExtra("ser_name",bookingSearchListData.ServiceName);
                            intent.putExtra("symptom",symptom);
                            intent.putExtra("diagnose",diagnose);
                            intent.putExtra("doc_name",doctorName);
                            intent.putExtra("date",bookingSearchListData.Date);
                            context.startActivity(intent);
                        }
                    }, 500);

                }
            });
            TextView name=convertView.findViewById(R.id.record_list_hospital_name);
            TextView service=convertView.findViewById(R.id.record_list_hospital_service);
            TextView date=convertView.findViewById(R.id.record_list_hospital_date);
            TextView time=convertView.findViewById(R.id.record_list_hospital_time);

            name.setText(bookingSearchListData.HospitalName);
            service.setText(bookingSearchListData.ServiceName);
            date.setText(bookingSearchListData.Date);
            time.setText(bookingSearchListData.Time);
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public int getViewTypeCount() {
        if(getCount()<1) return 1;
        return getCount();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
