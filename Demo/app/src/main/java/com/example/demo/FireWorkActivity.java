package com.example.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.demo.adapter.RlHomeAdapter;

import java.util.ArrayList;

public class FireWorkActivity extends AppCompatActivity {

    private RecyclerView rl;
    private FireworkyPullToRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_work);
        initView();
    }

    private void initView() {
        rl = (RecyclerView) findViewById(R.id.rl);
        pullToRefresh = (FireworkyPullToRefreshLayout) findViewById(R.id.pullToRefresh);
        rl.setLayoutManager(new LinearLayoutManager(this));
//        pullToRefresh.setRefreshing(isRestricted());
//        pullToRefresh.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        });

        pullToRefresh.setRefreshing(isRestricted());

    }
}
