package com.kaiser.reliability.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kaiser.reliability.R;
import com.kaiser.reliability.api.apiserver.ApiLoad;
import com.kaiser.reliability.base.BaseActivity;
import com.kaiser.reliability.baserx.RxManager;
import com.kaiser.reliability.baserx.RxSchedulers;
import com.kaiser.reliability.baserx.RxSubscriber;
import com.kaiser.reliability.bean.BaseBean;
import com.kaiser.reliability.load.bena.Users;
import com.kaiser.reliability.utils.StringUtil;
import com.kaiser.reliability.utils.ToastUitl;
import com.kaiser.reliability.view.CustomerPickerDialog;
import com.kaiser.reliability.view.LoopView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

public class MyInfoActivity extends BaseActivity implements View.OnClickListener{
    private CustomerPickerDialog mEducationDialog,mIsTFDialog,mJobTimeDialog;
    private TextView tvSchool,tvSave;
    private EditText etName,etPhone,etIDCar,etZhuAddress,etIDAddress,etHouseAddress,etFamilyNum,etXinCar,etRecord,etLoad,etSalay;
    private TextView tvTitle,tvBack,etMarry,etJob;
    private int jobTime=-1;

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
//
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
        etHouseAddress = (EditText) findViewById(R.id.etHouseAddress);
        etJob = (TextView) findViewById(R.id.etJob);
        etFamilyNum = (EditText) findViewById(R.id.etFamilyNum);
        etXinCar = (EditText) findViewById(R.id.etXinCar);
        etRecord = (EditText) findViewById(R.id.etRecord);
        etLoad = (EditText) findViewById(R.id.etLoad);
        etSalay = (EditText) findViewById(R.id.etSalay);

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
        tvSave.setOnClickListener(this);
    }

    @Override
    public void initData() {
        getinfo();
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
        if (mIsTFDialog == null) {
            final String[] strings = {"是", "否"};
            List<String> list = Arrays.asList(strings);
            LoopView.OnItemSelectedListener listener = new LoopView.OnItemSelectedListener() {
                private String[] types = {"01", "02"};

                @Override
                public void onItemSelected(int position) {
                    etMarry.setText(strings[position]);
                }
            };
            mIsTFDialog = new CustomerPickerDialog(virw.getContext(), list, listener);
        }
        mIsTFDialog.show();
    }

    private void showold(View view){
        if (mJobTimeDialog == null) {
            final String[] strings = {"0年","1年","2年","3年","4年","5年及以上",};
            List<String> list = Arrays.asList(strings);
            LoopView.OnItemSelectedListener listener = new LoopView.OnItemSelectedListener() {
                private int[] types = {0,1,2,3,4,5};

                @Override
                public void onItemSelected(int position) {
                    etJob.setText(strings[position]);
                    jobTime=types[position];
                }
            };
            mJobTimeDialog = new CustomerPickerDialog(view.getContext(), list, listener);
        }
        mJobTimeDialog.show();
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
            case R.id.tvSave:
                getUpinfo();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    private void getinfo(){
        Map<String,Object> map=new HashMap<>();
        startProgressDialog();
        RequestBody jsdata = ApiLoad.getInstance().jsdata(map);
        Observable<BaseBean<Users>> registUser = ApiLoad.getInstance().service.getUserInfo(jsdata);
        RxManager mRxManage = new RxManager();
        Observable<BaseBean<Users>> ss=  registUser.map(new Func1<BaseBean<Users>, BaseBean<Users>>() {
            @Override
            public BaseBean<Users> call(BaseBean<Users> seconData) {
                return seconData;
            }
        }).compose(RxSchedulers.<BaseBean<Users>>io_main());

        mRxManage.add(ss.subscribe(new RxSubscriber<BaseBean<Users>>(getApplicationContext(),false) {
            @Override
            protected void _onNext(BaseBean<Users> user) {
                try {
                    stopProgressDialog();
                    Log.e("TAG","------"+user.toString()+"b=="+user.getData().getOnlineId());
                    if (user.isOk()){
                        Users data = user.getData();
                        etName.setText(data.getUserName());
                        etFamilyNum.setText(data.getFamilymembers());
                        etIDCar.setText(data.getIdCard());
                        etIDAddress.setText(data.getHomeAddress());
                        etZhuAddress.setText(data.getNewAddress());
                        etHouseAddress.setText(data.getHouseAddress());
                        etFamilyNum.setText(data.getFamilymembers());
                        tvSchool.setText(data.getEducation());
                        etSalay.setText(data.getSalary());
                        etJob.setText(StringUtil.isEmpty(data.getJobTime())?"":data.getJobTime()+"年");
                        etMarry.setText(StringUtil.isEmpty(data.getIsMarry())?"":"1".equals(data.getIsMarry())?"是":"否");
                        etXinCar.setText(StringUtil.isEmpty(data.getIsCredit())?"":"1".equals(data.getIsCredit())?"是":"否");//是否有信用卡
                        etRecord.setText(StringUtil.isEmpty(data.getIsOverDur())?"":"1".equals(data.getIsOverDur())?"是":"否");//是否逾期
                        etLoad.setText(StringUtil.isEmpty(data.getIsLoad())?"":"1".equals(data.getIsOverDur())?"是":"否");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected void _onError(String message) {
                stopProgressDialog();
            }
        }));
    }

    private void getUpinfo(){
        String name = etName.getText().toString().trim();
        String idCard = etIDCar.getText().toString().trim();
        String idAdress = etIDAddress.getText().toString().trim();
        String homeAddress = etZhuAddress.getText().toString().trim();
        String houseAddress = etHouseAddress.getText().toString().trim();
        String marry = etMarry.getText().toString().trim();
//        String jobTime = etJob.getText().toString().trim();
        String familyNum = etFamilyNum.getText().toString().trim();
        String load = etLoad.getText().toString().trim();
        String xinCard = etXinCar.getText().toString().trim();
        String overDue = etRecord.getText().toString().trim();
        String salaty = etSalay.getText().toString().trim();
        String education = tvSchool.getText().toString().trim();
        Map<String,Object> map=new HashMap<>();
        map.put("userName",name);
        if (!StringUtil.isEmpty(marry)){
            map.put("isMarry","是".equals(marry)?"1":"0");
        }
        if (!StringUtil.isEmpty(xinCard)){
            map.put("isCredit","是".equals(xinCard)?"1":"0");
        }
        if (!StringUtil.isEmpty(load)){
            map.put("isLoad","是".equals(load)?"1":"0");
        }
        if (!StringUtil.isEmpty(overDue)){
            map.put("isOverDue","是".equals(load)?"1":"0");
        }
        if (!StringUtil.isEmpty(idCard)){
            map.put("idCard",idCard);
        }
        if (jobTime!=-1){
            map.put("jobTime",jobTime+"");
        }
        if (!StringUtil.isEmpty(idAdress)){
            map.put("homeAddress",idAdress);//身份地址
        }
        if (!StringUtil.isEmpty(homeAddress)){
            map.put("newAddress",homeAddress);//居住地址
        }
        if (!StringUtil.isEmpty(houseAddress)){
            map.put("houseAddress",houseAddress);
        }
        if (!StringUtil.isEmpty(familyNum)){
            map.put("familyMembers",familyNum);
        }
        if (!StringUtil.isEmpty(salaty)){
            map.put("salary",salaty);
        }
        if (!StringUtil.isEmpty(education)){
            map.put("education",education);
        }


        startProgressDialog();
        RequestBody jsdata = ApiLoad.getInstance().jsdata(map);

        Observable<BaseBean<Users>> registUser = ApiLoad.getInstance().service.getUpUsers(jsdata);

        RxManager mRxManage = new RxManager();
        Observable<BaseBean<Users>> ss=  registUser.map(new Func1<BaseBean<Users>, BaseBean<Users>>() {
            @Override
            public BaseBean<Users> call(BaseBean<Users> seconData) {
                return seconData;
            }
        }).compose(RxSchedulers.<BaseBean<Users>>io_main());

        mRxManage.add(ss.subscribe(new RxSubscriber<BaseBean<Users>>(getApplicationContext(),false) {
            @Override
            protected void _onNext(BaseBean<Users> user) {
                try {
                    stopProgressDialog();
                    Log.e("TAG","------"+user.toString()+"b=="+user.getData().getOnlineId());
                    if (user.isOk()){
                        ToastUitl.show("资料修改成功", Toast.LENGTH_SHORT);
                        finish();
//                        AppContext.setProperty(Config.Name,name);
//                        AppContext.setProperty(Config.Phone,phone);
                    }else {
                        ToastUitl.show("资料修改失败，请重新提交", Toast.LENGTH_SHORT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected void _onError(String message) {
                stopProgressDialog();
                ToastUitl.show("服务器连接失败，请重新提交", Toast.LENGTH_SHORT);
            }
        }));
    }

}
