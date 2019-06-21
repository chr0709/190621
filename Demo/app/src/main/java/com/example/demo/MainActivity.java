package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView nav;
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
//        initListener();
    }
//
//    private void initListener() {
//        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem pMenuItem) {
//                pMenuItem.setChecked(true);
//                switch (pMenuItem.getItemId()){
//                    case R.id.setting:
//                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        break;
//                }
//                return false;
//            }

//        });
//    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        nav = (NavigationView) findViewById(R.id.nav);
        dl = (DrawerLayout) findViewById(R.id.dl);
    }
}
