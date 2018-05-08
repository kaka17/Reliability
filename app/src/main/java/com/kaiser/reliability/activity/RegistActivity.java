package com.kaiser.reliability.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kaiser.reliability.R;
import com.kaiser.reliability.api.LoadClient;
import com.kaiser.reliability.api.ResponseBean;
import com.kaiser.reliability.api.apiserver.ApiLoad;
import com.kaiser.reliability.base.AppContext;
import com.kaiser.reliability.base.BaseActivity;
import com.kaiser.reliability.baserx.RxManager;
import com.kaiser.reliability.baserx.RxSchedulers;
import com.kaiser.reliability.baserx.RxSubscriber;
import com.kaiser.reliability.bean.BaseBean;
import com.kaiser.reliability.bean.SeconData;
import com.kaiser.reliability.bean.User;
import com.kaiser.reliability.configs.Config;
import com.kaiser.reliability.load.bena.Users;
import com.kaiser.reliability.utils.StringUtil;
import com.kaiser.reliability.utils.ToastUitl;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

public class RegistActivity extends BaseActivity implements View.OnClickListener{
    private EditText tvName,tvPhone,tvpwd01,tvPwd02;
    private TextView tvLogin,tvRegist,tvBack;
    private String name,phone,pwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_regist);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvName = (EditText) findViewById(R.id.tvName);
        tvPhone = (EditText) findViewById(R.id.tvPhone);
        tvpwd01 = (EditText) findViewById(R.id.tvpwd01);
        tvPwd02 = (EditText) findViewById(R.id.tvPwd02);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvRegist = (TextView) findViewById(R.id.tvRegist);
        tvBack = (TextView) findViewById(R.id.tvBack);
    }

    @Override
    public void setOnClicks() {
        tvLogin.setOnClickListener(this);
        tvRegist.setOnClickListener(this);
        tvBack.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvBack:
                finish();
                break;
            case R.id.tvRegist:
                if (isLogin()){
                    String mobile = tvPhone.getText().toString().trim();
                    if (StringUtil.isEmpty(mobile)){
                        ToastUitl.show("请填写电话号码", Toast.LENGTH_SHORT);
                        return;
                    }
                    Map<String,Object> map=new HashMap<>();
                    map.put("mobile",mobile);
                    map.put("password",pwd);
                    getRegist(ApiLoad.getInstance().jsdata(map));

                }


                break;
            case R.id.tvLogin:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));


                break;
        }
    }

    private boolean isLogin(){
         phone=tvPhone.getText().toString().trim();
         pwd=tvpwd01.getText().toString().trim();
        String pwd02=tvPwd02.getText().toString().trim();

        if (StringUtil.isEmpty(phone)){
            ToastUitl.showShort("请输入电话号码");
            return false;
        }
        if (StringUtil.isEmpty(pwd)){
            ToastUitl.showShort("请输入密码");
            return false;
        }
        if (! pwd.equals(pwd02)){
            ToastUitl.showShort("两次密码不一致");
            return false;
        }
//        AppContext.setProperty(Config.Name,name);
        AppContext.setProperty(Config.Phone,phone);
        return true;
    }
    private void getRegist(final RequestBody mobile){
        startProgressDialog();
            Observable<BaseBean<Users>> registUser = ApiLoad.getInstance().service.getRegistUser(mobile);

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
                        Log.e("TAG","------"+user.toString()+"b==");
                        if (user.isOk()){
                            AppContext.setProperty(Config.Name,name);
                            AppContext.setProperty(Config.Phone,phone);
                            AppContext.setProperty(Config.OnlineId,user.getData().getOnlineId());

                            startActivity(new Intent(getApplicationContext(),MyInfoActivity.class));
                            finish();
                        }else {

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

}
