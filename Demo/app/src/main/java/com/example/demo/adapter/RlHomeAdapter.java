package com.example.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.bean.HomeBean;

import java.util.List;

public class RlHomeAdapter extends RecyclerView.Adapter<RlHomeAdapter.MyHolder> {
    private List<HomeBean.DataBean.MaleBean> male;
    private Context mContext;

    public void setMale(List<HomeBean.DataBean.MaleBean> pMale) {
        male = pMale;
    }

    public RlHomeAdapter(List<HomeBean.DataBean.MaleBean> pMale, Context pContext) {
        male = pMale;
        mContext = pContext;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_homeitem, pViewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder pMyHolder, int pI) {
        pMyHolder.tv_title.setText(male.get(pI).getName());
        pMyHolder.tv_number.setText(male.get(pI).getBookCount());
        Glide.with(mContext).load("http://www.luliangdev.cn"+male.get(pI).getIcon()).placeholder(R.drawable.ic_loading_rotate).into(pMyHolder.home_img);


    }

    @Override
    public int getItemCount() {
        return male.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView home_img;
        private TextView tv_title;
        private TextView tv_number;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            home_img = itemView.findViewById(R.id.home_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_number = itemView.findViewById(R.id.tv_number);
        }
    }
}
