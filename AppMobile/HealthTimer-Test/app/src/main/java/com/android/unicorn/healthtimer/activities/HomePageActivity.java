package com.android.unicorn.healthtimer.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.fragments.BookingSearchListHospitalData;
import com.android.unicorn.healthtimer.fragments.The_Slide_Items_Model_Class_HomePage;
import com.android.unicorn.healthtimer.fragments.The_Slide_items_Pager_Adapter_HomePage;
import com.android.unicorn.healthtimer.viewmodels.HospitalData;
import com.android.unicorn.healthtimer.viewmodels.ListHospital;
import com.android.unicorn.healthtimer.viewmodels.UserData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageActivity extends AppCompatActivity {

    private List<The_Slide_Items_Model_Class_HomePage> listItems;
    private ViewPager page;
    private TabLayout tabLayout;
    private ScrollView srollview;
    private BottomNavigationView bottomNavigationView;
    private String phone;
    private Button helloUsername;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        UserData userData = UserData.getInstance();
        phone = userData.getPhone();

        page = findViewById(R.id.activity_home_page_my_pager) ;
        tabLayout = findViewById(R.id.activity_home_page_my_tablayout);

        helloUsername = findViewById(R.id.activity_home_page_button_hello_username);
        helloUsername.setText("XIN CHÀO " + phone);

        bottomNavigationView = findViewById(R.id.ativity_home_page_bottom_navigation);

        // Make a copy of the slides you'll be presenting.
        listItems = new ArrayList<>() ;
        listItems.add(new The_Slide_Items_Model_Class_HomePage(R.drawable.hospital_image_test_1,"Bệnh viện Hoàn Mỹ"));
        listItems.add(new The_Slide_Items_Model_Class_HomePage(R.drawable.hospital_image_test_2,"Bệnh viện Đại học Y dược"));
        listItems.add(new The_Slide_Items_Model_Class_HomePage(R.drawable.hospital_image_test_3,"Bệnh viện quận Phú Nhuận"));


        The_Slide_items_Pager_Adapter_HomePage itemsPager_adapter = new The_Slide_items_Pager_Adapter_HomePage(this, listItems);
        page.setAdapter(itemsPager_adapter);

        // The_slide_timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
        tabLayout.setupWithViewPager(page,true);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.activity_home_page_bottom_nevigation_booking:
                        Intent intent_booking = new Intent(getApplicationContext(), BookingSearchActivity.class);
                        startActivity(intent_booking);
                        break;
                    case R.id.activity_home_page_bottom_nevigation_schedule:
                        Intent intent_schedule = new Intent(getApplicationContext(), ScheduleActivity.class);
                        startActivity(intent_schedule);
                        break;
                    case R.id.activity_home_page_bottom_nevigation_record:
                        Intent intent_record = new Intent(getApplicationContext(), RecordActivity.class);
                        startActivity(intent_record);
                        break;
                    case R.id.activity_home_page_bottom_nevigation_account:
                        Intent intent_account = new Intent(getApplicationContext(), AccountActivity.class);
                        startActivity(intent_account);
                        break;
                }
                return true;
            }
        });
    }

    public class The_slide_timer extends TimerTask {
        @Override
        public void run() {

            HomePageActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (page.getCurrentItem()< listItems.size()-1) {
                        page.setCurrentItem(page.getCurrentItem()+1);
                    }
                    else
                        page.setCurrentItem(0);
                }
            });
        }
    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Đóng ứng dụng")
                .setMessage("Bạn có muốn thoát ứng dụng này?")
                .setPositiveButton("Thoát", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                        int pid = android.os.Process.myPid();
                        android.os.Process.killProcess(pid);
                        System.exit(0);
                    }

                })
                .setNegativeButton("Không", null)
                .show();
    }


}
