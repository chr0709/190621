package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.demo.adapter.RlHomeAdapter;
import com.example.demo.api.MyServer;
import com.example.demo.bean.HomeBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rl;
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        mToken = intent.getStringExtra("token");
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.login)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<HomeBean> android = myServer.homeData("Android", mToken);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean pHomeBean) {
                        if (pHomeBean!=null){
                            List<HomeBean.DataBean.MaleBean> male = pHomeBean.getData().getMale();
                            RlHomeAdapter adapter = new RlHomeAdapter(male, HomeActivity.this);
                            rl.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(HomeActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView() {
        rl = (RecyclerView) findViewById(R.id.rl);
        rl.setLayoutManager(new LinearLayoutManager(this));

    }
}
