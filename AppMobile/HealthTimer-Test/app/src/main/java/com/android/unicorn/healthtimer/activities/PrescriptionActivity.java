package com.android.unicorn.healthtimer.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.viewmodels.UserData;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PrescriptionActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        Bundle extras = getIntent().getExtras();

        UserData userData = UserData.getInstance();
        String fullname = userData.getFullname();
        if (fullname != null){
            TextView textfullname = findViewById(R.id.activity_prescription_user_name);
            textfullname.setText(fullname);
        }

        String HospitalName = extras.getString("hos_name");
        TextView texthos_name = findViewById(R.id.activity_prescription_hospital_name);
        texthos_name.setText(HospitalName);

        String service = extras.getString("ser_name");
        TextView textser_name = findViewById(R.id.activity_prescription_service);
        textser_name.setText(service);

        String diagnose = extras.getString("diagnose");
        TextView textdiagnose = findViewById(R.id.activity_prescription_user_diagnose);
        textdiagnose.setText(diagnose);

        String docname = extras.getString("doc_name");
        TextView textdocname = findViewById(R.id.activity_prescription_doc_name);
        textdocname.setText(docname);

        String inputdate = extras.getString("date");
        String[] temp = inputdate.split("/");
        String date = "Ngày " + temp[0] + " tháng " + temp[1] + " năm " + temp[2];
        TextView textday = findViewById(R.id.activity_prescription_day);
        textday.setText(date);

        TextView textprescription = findViewById(R.id.activity_prescription_prescription);
        String prescription = "";
        ArrayList<String> prescriptionsNameList = (ArrayList<String>) getIntent().getSerializableExtra("prescriptionsNameList");
        ArrayList<String> prescriptionsAmountList = (ArrayList<String>) getIntent().getSerializableExtra("prescriptionsAmountList");
        for (int i = 0; i < prescriptionsNameList.size(); i++){
            prescription = prescription + String.valueOf(i + 1) +  ". Tên thuốc: " + prescriptionsNameList.get(i).toString() + System.getProperty ("line.separator");
            String tempamount = prescriptionsAmountList.get(i).toString();
            if (tempamount.equals("00000000")){
                prescription = prescription + System.getProperty ("line.separator");
                prescription = prescription + System.getProperty ("line.separator");
                prescription = prescription + System.getProperty ("line.separator");
            }
            else {
                String[] tempsplit = tempamount.split("");

                int indextemp0 = Integer.parseInt(tempsplit[0]) + Integer.parseInt(tempsplit[1]);
                int indextemp1 = Integer.parseInt(tempsplit[2]) + Integer.parseInt(tempsplit[3]);
                int indextemp2 = Integer.parseInt(tempsplit[4]) + Integer.parseInt(tempsplit[5]);
                int indextemp3 = Integer.parseInt(tempsplit[6]) + Integer.parseInt(tempsplit[7]);
                prescription = prescription + "Sáng: " + String.valueOf(indextemp0) + "   Trưa: "+ String.valueOf(indextemp1) + "   Chiều: "+ String.valueOf(indextemp2) + "   Tối: "+ String.valueOf(indextemp3) + System.getProperty ("line.separator");

                prescription = prescription + System.getProperty ("line.separator");
            }
        }
        textprescription.setText(prescription);
    }
}
