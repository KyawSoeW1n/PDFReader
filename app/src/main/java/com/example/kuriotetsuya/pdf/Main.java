package com.example.kuriotetsuya.pdf;

/**
 * Created by Kurio Tetsuya on 4/4/2017.
 */

import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

public class Main extends AppCompatActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
    ViewPager vp;
    PagerAdapter data;
    ActionBar ab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        vp = (ViewPager) findViewById(R.id.pager);
        FragmentManager fm = getSupportFragmentManager();
        data = new PagerAdapter(fm);
        vp.setAdapter(data);
        vp.setOnPageChangeListener(this);
        ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab t_1 = ab.newTab();
        t_1.setText("BôôkList");
        t_1.setTabListener(this);
        ActionBar.Tab t_2 = ab.newTab();
        t_2.setText("Bookmark");
        t_2.setTabListener(this);
        ab.addTab(t_1);
        ab.addTab(t_2);
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){

        switch(permsRequestCode){

            case 200:

                boolean locationAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                boolean cameraAccepted = grantResults[1]== PackageManager.PERMISSION_GRANTED;

                break;

        }

    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        vp.setCurrentItem(tab.getPosition());

    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    public void onPageSelected(int position) {
        ab.setSelectedNavigationItem(position);
    }

    public void onPageScrollStateChanged(int state) {

    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

