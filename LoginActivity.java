package com.example.hotelmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.hotelmanagementsystem.R;
import com.example.hotelmanagementsystem.Adapter.LoginAdapter;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Customer Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Admin Login"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(loginAdapter);

    }
}