package com.kaiser.reliability.activity;

import android.os.Bundle;
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
    private EditText etName,etPhone,etIDCar,etZhuAddress,etIDAddress,etMarry,etJob,etFamilyNum,etXinCar,etRecord,etWages;

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
        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etIDCar = (EditText) findViewById(R.id.etIDCar);
        etIDAddress = (EditText) findViewById(R.id.etIDAddress);
        etZhuAddress = (EditText) findViewById(R.id.etZhuAddress);
        etMarry = (EditText) findViewById(R.id.etMarry);
        etWages = (EditText) findViewById(R.id.etWages);
        etJob = (EditText) findViewById(R.id.etJob);
        etFamilyNum = (EditText) findViewById(R.id.etFamilyNum);
        etXinCar = (EditText) findViewById(R.id.etXinCar);
        etRecord = (EditText) findViewById(R.id.etRecord);

        tvSchool= (TextView) findViewById(R.id.tvSchool);
        tvSave = (TextView) findViewById(R.id.tvSave);
        tvSave.setVisibility(View.VISIBLE);
    }

    @Override
    public void setOnClicks() {
        tvSchool.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        showEducationDialog(v);
        startPlayerTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlayerTimer();
    }

    private PlayerTimer playTimer;
    /**
     * start Timer
     */
    protected synchronized void startPlayerTimer() {
        stopPlayerTimer();
        if (playTimer == null) {
            Log.e("TAG","------>"+"sta palaTime="+Thread.currentThread());
            playTimer = new PlayerTimer();
            Timer m_musictask = new Timer();
            m_musictask.schedule(playTimer, 30000);
        }
    }

    /**
     * stop Timer
     */
    protected synchronized void stopPlayerTimer() {
        try {
            if (playTimer != null) {
                playTimer.cancel();
                playTimer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class PlayerTimer extends TimerTask {

        public PlayerTimer() {
        }

        public void run() {
            //execute task
            Log.e("TAG","------>"+"palaTime="+Thread.currentThread());
            stopPlayerTimer();
        }
    }
}
