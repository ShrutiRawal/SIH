package com.example.sih;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AnalyticsActivity extends AppCompatActivity {

    public int ActivityNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        setUpBottomNavigationView();
    }

    private void setUpBottomNavigationView(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavViewBar);
        BottomNavigationHelper.enableNavigation(AnalyticsActivity.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ActivityNum);
        menuItem.setChecked(true);
    }

}
