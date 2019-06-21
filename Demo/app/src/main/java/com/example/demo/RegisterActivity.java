package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.api.MyServer;
import com.example.demo.bean.RegisterBean;
import com.example.demo.util.MD5Util;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_register_name;
    private EditText et_register_psw;
    private EditText et_register_psw2;
    private Button btn_registe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    private void initView() {
        et_register_name = (EditText) findViewById(R.id.et_register_name);
        et_register_psw = (EditText) findViewById(R.id.et_register_psw);
        et_register_psw2 = (EditText) findViewById(R.id.et_register_psw2);
        btn_registe = (Button) findViewById(R.id.btn_registe);

        btn_registe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registe:
                register();
                break;
        }
    }

    private void register() {
        notnull();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.login)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);

        final String name = et_register_name.getText().toString();
        final String psw = et_register_psw.getText().toString();
        Observable<RegisterBean> re = myServer.data("Android", "weiyue",
                name, psw);
        re.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean pRegisterBean) {
                        if (pRegisterBean !=null){
                            if (pRegisterBean.getCode() == 10000){
                                String ppp = MD5Util.md5(psw);
                                Log.i("tag", "onNextRegister: "+pRegisterBean.getCode()+"------"+pRegisterBean.getMsg().toString());
                                Intent intent = new Intent();
                                intent.putExtra("na",name);
                                intent.putExtra("psw",psw);
                                setResult(200,intent);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void notnull() {
        final String nameString = et_register_name.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        final String pswString = et_register_psw.getText().toString().trim();
        if (TextUtils.isEmpty(pswString)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String psw2String = et_register_psw2.getText().toString().trim();
        if (TextUtils.isEmpty(psw2String)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pswString.equals(psw2String)){
            Toast.makeText(this, "两个密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
    }


}
