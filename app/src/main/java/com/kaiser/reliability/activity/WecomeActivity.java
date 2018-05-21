package com.kaiser.reliability.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.kaiser.reliability.R;
import com.kaiser.reliability.adapter.WeComeAdapter;
import com.kaiser.reliability.base.AppContext;
import com.kaiser.reliability.base.BaseActivity;
import com.kaiser.reliability.configs.Config;

import java.util.ArrayList;
import java.util.List;

public class WecomeActivity extends BaseActivity {

    private ViewPager mPager;
    private WeComeAdapter adapter;
    private List<View> views=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_wecome;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mPager = (ViewPager) findViewById(R.id.mPager);
        getView();
        adapter=new WeComeAdapter(views,getApplicationContext());
        mPager.setAdapter(adapter);
    }

    @Override
    public void setOnClicks() {

    }

    @Override
    public void initData() {
//        ImageView imageView=new ImageView(getApplicationContext());
//        imageView.setImageResource(R.drawable.icon);
//        ImageView imageView02=new ImageView(getApplicationContext());
//        imageView02.setImageResource(R.drawable.icon);
//        View view03 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.wecome_item03, null);
//        view03.findViewById(R.id.tvGoIn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppContext.setProperty(Config.IsFirstIn,Config.IsFirstIn);
//                startActivity(new Intent(getApplicationContext(),ToLoanActivity.class));
//
//            }
//        });
//        views.add(imageView);
//        views.add(imageView02);
//        views.add(view03);
    }
    private void getView(){
        ImageView imageView=new ImageView(getApplicationContext());
        imageView.setImageResource(R.mipmap.wecome001);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView02=new ImageView(getApplicationContext());
        imageView02.setImageResource(R.mipmap.wecome002);
        imageView02.setScaleType(ImageView.ScaleType.FIT_XY);
        View view03 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.wecome_item03, null);
        view03.findViewById(R.id.tvGoIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppContext.setProperty(Config.IsFirstIn,Config.IsFirstIn);
                startActivity(new Intent(getApplicationContext(),ToLoanActivity.class));

            }
        });
        views.add(imageView);
        views.add(imageView02);
        views.add(view03);
    }
}
