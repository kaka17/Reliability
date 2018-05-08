package com.kaiser.reliability.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kaiser.reliability.R;
import com.kaiser.reliability.api.apiserver.ApiLoad;
import com.kaiser.reliability.base.AppContext;
import com.kaiser.reliability.base.BaseActivity;
import com.kaiser.reliability.baserx.RxManager;
import com.kaiser.reliability.baserx.RxSchedulers;
import com.kaiser.reliability.baserx.RxSubscriber;
import com.kaiser.reliability.bean.BaseBean;
import com.kaiser.reliability.configs.Config;
import com.kaiser.reliability.load.bena.Users;
import com.kaiser.reliability.utils.StringUtil;
import com.kaiser.reliability.utils.ToastUitl;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText tvName,tvPhone,tvpwd;
        private TextView tvLogin,tvRegist,tvBack;
    private  String phone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvName = (EditText) findViewById(R.id.tvName);
        tvPhone = (EditText) findViewById(R.id.tvPhone);
        tvpwd = (EditText) findViewById(R.id.tvCare);
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
                startActivity(new Intent(getApplicationContext(),RegistActivity.class));
                finish();
                break;
            case R.id.tvLogin:
                if (islogin()){
//                    startActivity(new Intent(getApplicationContext(),ToLoanActivity.class));
//                    AppContext.setProperty(Config.ISLOGINGING,Config.ISLOGINGING);

                }
                break;
        }
    }
    private boolean islogin(){
        phone=tvPhone.getText().toString().trim();
        String pwd=tvpwd.getText().toString().trim();
        if (StringUtil.isEmpty(phone)){
            ToastUitl.showShort("请输入电话号码");
            return false;
        }
        if (StringUtil.isEmpty(pwd)){
            ToastUitl.showShort("请输入密码");
            return false;
        }
        AppContext.setProperty(Config.Pwd,pwd);
        AppContext.setProperty(Config.Phone,phone);

        getLogin(pwd);
        return true;
    }

    private void getLogin(String pwd){
        Map<String,Object> map=new HashMap<>();
        map.put("password",pwd);
        startProgressDialog();
        RequestBody jsdata = ApiLoad.getInstance().jsdata(map);

        Observable<BaseBean<Users>> registUser = ApiLoad.getInstance().service.getLoginUser(jsdata);

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
                        AppContext.setProperty(Config.Phone,phone);
                        startActivity(new Intent(getApplicationContext(),ToLoanActivity.class));
                    AppContext.setProperty(Config.ISLOGINGING,Config.ISLOGINGING);
                        AppContext.setProperty(Config.OnlineId,user.getData().getOnlineId());
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
