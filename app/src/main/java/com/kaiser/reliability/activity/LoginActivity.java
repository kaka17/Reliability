package com.kaiser.reliability.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kaiser.reliability.R;
import com.kaiser.reliability.base.AppContext;
import com.kaiser.reliability.base.BaseActivity;
import com.kaiser.reliability.configs.Config;
import com.kaiser.reliability.utils.StringUtil;
import com.kaiser.reliability.utils.ToastUitl;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText tvName,tvPhone,tvCare;
    private TextView tvLogin,tvRegist;


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
        tvCare = (EditText) findViewById(R.id.tvCare);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvRegist = (TextView) findViewById(R.id.tvRegist);
    }

    @Override
    public void setOnClicks() {
        tvLogin.setOnClickListener(this);
        tvRegist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvRegist:
                startActivity(new Intent(getApplicationContext(),RegistActivity.class));
                break;
            case R.id.tvLogin:
                if (islogin()){
                    startActivity(new Intent(getApplicationContext(),ToLoanActivity.class));
                    AppContext.setProperty(Config.ISLOGINGING,Config.ISLOGINGING);
                }
                break;
        }
    }
    private boolean islogin(){
        String name=tvName.getText().toString().trim();
        String phone=tvPhone.getText().toString().trim();

        if (StringUtil.isEmpty(name)){
            ToastUitl.showShort("请输入真实姓名");
            return false;
        }
        if (StringUtil.isEmpty(phone)){
            ToastUitl.showShort("请输入电话号码");
            return false;
        }
        AppContext.setProperty(Config.Name,name);
        AppContext.setProperty(Config.Phone,phone);
        return true;
    }
}
