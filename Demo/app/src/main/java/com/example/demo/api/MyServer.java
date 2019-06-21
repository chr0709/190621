package com.example.demo.api;

import com.example.demo.bean.HomeBean;
import com.example.demo.bean.LoginBean;
import com.example.demo.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyServer {
    public String login = "http://www.luliangdev.cn/";

    @GET("api/user/login")
    Observable<LoginBean> login(@Header("app-type") String type,
                                @Query("name") String name,
                                @Query("password") String psw);

    @POST("api/user/register")
    @FormUrlEncoded
    Observable<RegisterBean> data(@Header("app-type") String type,
                                  @Header("app-access-token") String token,
                                  @Field("name") String name,
                                  @Field("password") String psw);

    @GET("api/classify")
    Observable<HomeBean> homeData(@Header("app-type") String type,
                                  @Header("app-access-token") String token);
}
