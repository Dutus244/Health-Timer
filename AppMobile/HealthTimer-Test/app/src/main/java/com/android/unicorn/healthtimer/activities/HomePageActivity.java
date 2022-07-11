package com.android.unicorn.healthtimer.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.unicorn.healthtimer.R;
import com.android.unicorn.healthtimer.The_Slide_Items_Model_Class_HomePage;
import com.android.unicorn.healthtimer.The_Slide_items_Pager_Adapter_HomePage;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class HomePageActivity extends AppCompatActivity {

    private List<The_Slide_Items_Model_Class_HomePage> listItems;
    private ViewPager page;
    private TabLayout tabLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        page = findViewById(R.id.activity_home_page_my_pager) ;
        tabLayout = findViewById(R.id.activity_home_page_my_tablayout);

        // Make a copy of the slides you'll be presenting.
        listItems = new ArrayList<>() ;
        listItems.add(new The_Slide_Items_Model_Class_HomePage(R.drawable.hospital_image_test_1,"Bệnh viện Hoàn Mỹ"));
        listItems.add(new The_Slide_Items_Model_Class_HomePage(R.drawable.hospital_image_test_2,"Bệnh viện Đại học Y dược"));
        listItems.add(new The_Slide_Items_Model_Class_HomePage(R.drawable.hospital_image_test_3,"Bệnh viện quận Phú Nhuận"));


        The_Slide_items_Pager_Adapter_HomePage itemsPager_adapter = new The_Slide_items_Pager_Adapter_HomePage(this, listItems);
        page.setAdapter(itemsPager_adapter);

        // The_slide_timer
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
        tabLayout.setupWithViewPager(page,true);
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
        finish();
        System.exit(0);
    }
}
