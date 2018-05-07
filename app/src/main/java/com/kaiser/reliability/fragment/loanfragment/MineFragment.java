package com.kaiser.reliability.fragment.loanfragment;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.kaiser.reliability.R;
import com.kaiser.reliability.activity.LoginActivity;
import com.kaiser.reliability.activity.MyInfoActivity;
import com.kaiser.reliability.activity.RegistActivity;
import com.kaiser.reliability.base.AppContext;
import com.kaiser.reliability.base.BaseFragment;
import com.kaiser.reliability.configs.Config;
import com.kaiser.reliability.utils.StringUtil;
import com.kaiser.reliability.view.NoDataPop;
import com.kaiser.reliability.view.TakeLoginPop;

import butterknife.BindView;

/**
 * Created by ex-huangkeze001 on 2018/4/19.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.reJieDai)
    RelativeLayout reJieDai;
    @BindView(R.id.reyouhuiquan)
    RelativeLayout reyouhuiquan;
    @BindView(R.id.reyinhangka)
    RelativeLayout reyinhangka;
    @BindView(R.id.rezilian)
    RelativeLayout rezilian;
    @BindView(R.id.rexinyongka)
    RelativeLayout rexinyongka;
    @BindView(R.id.reYaoqing)
    RelativeLayout reYaoqing;
    @BindView(R.id.reSet)
    RelativeLayout reSet;
    @BindView(R.id.reLoginOut)
    RelativeLayout reLoginOut;
    private Context mContext;
    private TakeLoginPop takeLoginPop;
    private NoDataPop noDataPop;

    @Override
    protected int getLayoutResource() {
        mContext=getActivity();
        return R.layout.mine_fragment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setMyOnClickListener() {
        reJieDai.setOnClickListener(this);
        reyouhuiquan.setOnClickListener(this);
        reyinhangka.setOnClickListener(this);
        rezilian.setOnClickListener(this);
        rexinyongka.setOnClickListener(this);
        reYaoqing.setOnClickListener(this);
        reSet.setOnClickListener(this);
        reLoginOut.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.reJieDai:
                if (StringUtil.isEmpty(AppContext.getProperty(Config.Phone))){
                    showPop(v);
                }else {
                    showChangInfoPop(v);
                }
                break;
            case R.id.reyouhuiquan:
                if (StringUtil.isEmpty(AppContext.getProperty(Config.Phone))){
                    showPop(v);
                }else {
                    showChangInfoPop(v);
                }
                break;
            case R.id.reyinhangka:
                if (StringUtil.isEmpty(AppContext.getProperty(Config.Phone))){
                    showPop(v);
                }else {
                    showChangInfoPop(v);
                }
                break;
            case R.id.rezilian:
                if (StringUtil.isEmpty(AppContext.getProperty(Config.Phone))){
                    showPop(v);
                }else {
                    startActivity(new Intent(getActivity(), MyInfoActivity.class));
                }
                break;
            case R.id.rexinyongka:
                if (StringUtil.isEmpty(AppContext.getProperty(Config.Phone))){
                    showPop(v);
                }else {
                    showChangInfoPop(v);
                }
                break;
            case R.id.reYaoqing:
                if (StringUtil.isEmpty(AppContext.getProperty(Config.Phone))){
                    showPop(v);
                }else {
                    showChangInfoPop(v);
                }
                break;
            case R.id.reSet:
                if (StringUtil.isEmpty(AppContext.getProperty(Config.Phone))){
                    showPop(v);
                }else {
                    showChangInfoPop(v);
                }
                break;
            case R.id.reLoginOut:
                AppContext.clearPropertyData();
                getActivity().finish();
                break;

        }


    }
    private void showPop(View view){
        if (takeLoginPop==null|| ! takeLoginPop.isShowing()){
            takeLoginPop=new TakeLoginPop(mContext, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (v.getId()){
                        case R.id.tvLogin:
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            takeLoginPop.dismiss();
                            break;
                        case R.id.tvRegist:
                            takeLoginPop.dismiss();
                            startActivity(new Intent(getActivity(), RegistActivity.class));
                            break;
                    }
                }
            });
            backgroundAlpha(0.6f);
            takeLoginPop.setFocusable(true);
            takeLoginPop.showAtLocation(view, Gravity.BOTTOM,0,0);
            takeLoginPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);
                }
            });
        }else {
            takeLoginPop.dismiss();
        }
    }
    private void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams layoutParams=getActivity().getWindow().getAttributes();
        layoutParams.alpha=bgAlpha;
        getActivity().getWindow().setAttributes(layoutParams);
    }


    private void showChangInfoPop(View view){
        if (noDataPop==null|| ! noDataPop.isShowing()){
            noDataPop=new NoDataPop(mContext, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.tvChangInfo:
                            noDataPop.dismiss();
                            startActivity(new Intent(getActivity(),MyInfoActivity.class));
                            break;

                    }
                }
            });
            backgroundAlpha(0.6f);
            noDataPop.setFocusable(true);
            noDataPop.showAtLocation(view, Gravity.BOTTOM,0,0);
            noDataPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);
                }
            });
        }else {
            noDataPop.dismiss();
        }
    }
}
