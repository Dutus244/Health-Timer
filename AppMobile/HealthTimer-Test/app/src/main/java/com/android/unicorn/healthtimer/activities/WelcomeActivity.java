package com.android.unicorn.healthtimer.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.unicorn.healthtimer.R;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private IntroViewPagerAdapter mPagerAdapter;
    private LinearLayout mDotsLayout;
    private TextView[] mDots;
    private int[] mLayouts;
    private Button mBtnSkip;
    private Button mBtnNext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();

        if (isItFirestTime()) {

        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        //Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        }
        setContentView(R.layout.activity_welcome);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mDotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        mBtnSkip = (Button) findViewById(R.id.btn_skip);
        mBtnNext = (Button) findViewById(R.id.btn_next);

        /**
         * Layouts of all welcome slides
         * add few more layouts if you want
         */
        mLayouts = new int[]{R.layout.activity_welcome_slide1, R.layout.activity_welcome_slide2, R.layout.activity_welcome_slide3, R.layout.activity_welcome_slide4};
        addBottomDots(0);
        changeStatusBarColor();

        mPagerAdapter = new IntroViewPagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mViewPagerChangeListener);

        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Checking for last page if last page home screen will be launched
                 */
                int current = getItem(1);
                if (current < mLayouts.length) {
                    // move to nex screen
                    mViewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }

            }
        });
    }

    private ViewPager.OnPageChangeListener mViewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            //Change the next button text 'NEXT'/'GOT IT'
            if (position == mLayouts.length - 1) {
                mBtnNext.setText(getString(R.string.start));
                mBtnSkip.setVisibility(View.GONE);
            } else {
                //Still pages are left
                mBtnNext.setText(getString(R.string.next));
                mBtnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void addBottomDots(int currentPage) {
        mDots = new TextView[mLayouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInActive = getResources().getIntArray(R.array.array_dot_inactive);

        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("•"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(colorsInActive[currentPage]);
            mDotsLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[currentPage].setTextColor(colorsActive[currentPage]);
        }
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public class IntroViewPagerAdapter extends PagerAdapter {
        private LayoutInflater mInflater;

        public IntroViewPagerAdapter() {
            super();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mInflater.inflate(mLayouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return mLayouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    public boolean isItFirestTime() {
        if (sharedPreferences.getBoolean("firstTime", true)) {
            sharedEditor.putBoolean("firstTime", false);
            sharedEditor.commit();
            sharedEditor.apply();
            return true;
        } else {
            return false;
        }
    }
}