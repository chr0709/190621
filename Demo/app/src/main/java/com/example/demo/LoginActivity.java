package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.api.MyServer;
import com.example.demo.bean.LoginBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_login_name;
    private EditText et_login_psw;
    private Button btn_register;
    private Button btn_login;
    private TextView tv_unlogin;
    private String mNa;
    private String mPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_login_name = (EditText) findViewById(R.id.et_login_name);
        et_login_psw = (EditText) findViewById(R.id.et_login_psw);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_unlogin = (TextView) findViewById(R.id.tv_unlogin);

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,100);
                break;
            case R.id.btn_login:
                initLogin();
                break;
            case R.id.tv_unlogin:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100&& resultCode == 200){
            mNa = data.getStringExtra("na");
            mPsw = data.getStringExtra("psw");
            et_login_name.setText(mNa);
            et_login_psw.setText(mPsw);
        }
    }

    private void initLogin() {
        String s = et_login_name.getText().toString();
        String s1 = et_login_psw.getText().toString();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.login)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<LoginBean> login = myServer.login("Android", s, s1);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean pLoginBean) {
                        if (pLoginBean !=null){
                            if (pLoginBean.getCode()==10000){
                                Log.i("tag", "onNextlogin success: "+pLoginBean.getMsg()+pLoginBean.getCode());
                                Toast.makeText(LoginActivity.this, pLoginBean.getMsg(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                intent.putExtra("token",pLoginBean.getData().getToken());
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this, pLoginBean.getMsg(), Toast.LENGTH_SHORT).show();
                                Log.i("tag", "登录 错误: "+pLoginBean.getMsg()+"----"+pLoginBean.getCode());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void submit() {
        // validate
        String name = et_login_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请给自己一个喜欢的名字吧！", Toast.LENGTH_SHORT).show();
            return;
        }

        String psw = et_login_psw.getText().toString().trim();
        if (TextUtils.isEmpty(psw)) {
            Toast.makeText(this, "输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something


    }
}
