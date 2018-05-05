package com.kaiser.reliability.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kaiser.reliability.R;
import com.kaiser.reliability.base.BaseActivity;
import com.kaiser.reliability.view.CustomerPickerDialog;
import com.kaiser.reliability.view.LoopView;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyInfoActivity extends BaseActivity implements View.OnClickListener{
    private CustomerPickerDialog mEducationDialog;
    private TextView tvSchool,tvSave;
    private EditText etName,etPhone,etIDCar,etZhuAddress,etIDAddress,etFamilyNum,etXinCar,etRecord,etWages;
    private TextView tvTitle,tvBack,etMarry,etJob;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_info);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvTitle.setText("我的资料");
        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etIDCar = (EditText) findViewById(R.id.etIDCar);
        etIDAddress = (EditText) findViewById(R.id.etIDAddress);
        etZhuAddress = (EditText) findViewById(R.id.etZhuAddress);
        etMarry = (TextView) findViewById(R.id.etMarry);
        etWages = (EditText) findViewById(R.id.etWages);
        etJob = (TextView) findViewById(R.id.etJob);
        etFamilyNum = (EditText) findViewById(R.id.etFamilyNum);
        etXinCar = (EditText) findViewById(R.id.etXinCar);
        etRecord = (EditText) findViewById(R.id.etRecord);

        tvSchool= (TextView) findViewById(R.id.tvSchool);
        tvSave = (TextView) findViewById(R.id.tvSave);
        tvSave.setVisibility(View.VISIBLE);
    }

    @Override
    public void setOnClicks() {
        tvBack.setOnClickListener(this);
        tvSchool.setOnClickListener(this);
        etMarry.setOnClickListener(this);
        etJob.setOnClickListener(this);
    }


    /**
     * 学历
     *
     * @param v
     */
    private void showEducationDialog(View v) {
        if (mEducationDialog == null) {
            final String[] strings = {"博士后", "博士研究生", "硕士研究生", "大学本科生", "大学专科", "中专", "高中", "初中", "小学", "小学以下"};
            List<String> list = Arrays.asList(strings);
            LoopView.OnItemSelectedListener listener = new LoopView.OnItemSelectedListener() {
                private String[] types = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};

                @Override
                public void onItemSelected(int position) {
//                    setInfoTypeAndText(strings[position], types[position]);
                    tvSchool.setText(strings[position]);
                }
            };
            mEducationDialog = new CustomerPickerDialog(v.getContext(), list, listener);
//            mEducationDialog.setSelectedPosition(list.indexOf(mPersonalInfo.get(realPosition).infoValue));


//                mEducationDialog.setMenuItemClickListener(new CustomerSourceDialog.OnMenuItemClickListener() {
//                    private String[] types = {"01","02","03","04","05","06","07","08","09","10"};
//
//                    @Override
//                    public void onMenuItemClick(int position) {
//                        setInfoTypeAndText(strings[position],types[position]);
//                    }
//                });
        }
        mEducationDialog.show();
    }
    private void showTrueOrFalse(View virw) {
        if (mEducationDialog == null) {
            final String[] strings = {"是", "否"};
            List<String> list = Arrays.asList(strings);
            LoopView.OnItemSelectedListener listener = new LoopView.OnItemSelectedListener() {
                private String[] types = {"01", "02"};

                @Override
                public void onItemSelected(int position) {
                    etMarry.setText(strings[position]);
                }
            };
            mEducationDialog = new CustomerPickerDialog(virw.getContext(), list, listener);
        }
        mEducationDialog.show();
    }

    private void showold(View view){
        if (mEducationDialog == null) {
            final String[] strings = {"无","1年","2年","3年","4叔","5年","5年以上"};
            List<String> list = Arrays.asList(strings);
            LoopView.OnItemSelectedListener listener = new LoopView.OnItemSelectedListener() {
                private String[] types = {"01", "02"};

                @Override
                public void onItemSelected(int position) {
                    etJob.setText(strings[position]);
                }
            };
            mEducationDialog = new CustomerPickerDialog(view.getContext(), list, listener);
        }
        mEducationDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvBack:
                finish();
                break;
            case R.id.tvSchool:
                showEducationDialog(v);
                break;
            case R.id.etMarry:
                showTrueOrFalse(v);
                break;
            case R.id.etJob:
                showold(v);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
