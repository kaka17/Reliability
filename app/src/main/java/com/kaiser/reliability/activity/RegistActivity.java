package com.kaiser.reliability.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kaiser.reliability.R;
import com.kaiser.reliability.base.AppContext;
import com.kaiser.reliability.base.BaseActivity;
import com.kaiser.reliability.configs.Config;
import com.kaiser.reliability.utils.StringUtil;
import com.kaiser.reliability.utils.ToastUitl;

public class RegistActivity extends BaseActivity implements View.OnClickListener{
    private EditText tvName,tvPhone,tvpwd01,tvPwd02;
    private TextView tvLogin,tvRegist;

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
                if (isLogin()){
                    startActivity(new Intent(getApplicationContext(),MyInfoActivity.class));
                    AppContext.setProperty(Config.ISLOGINGING,Config.ISLOGINGING);

                }
                break;
            case R.id.tvLogin:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
        }
    }

    private boolean isLogin(){
        String name=tvName.getText().toString().trim();
        String phone=tvPhone.getText().toString().trim();
        String pwd01=tvpwd01.getText().toString().trim();
        String pwd02=tvPwd02.getText().toString().trim();

        if (StringUtil.isEmpty(name)){
            ToastUitl.showShort("请输入真实姓名");
            return false;
        }
        if (StringUtil.isEmpty(phone)){
            ToastUitl.showShort("请输入电话号码");
            return false;
        }
        if (StringUtil.isEmpty(pwd01)){
            ToastUitl.showShort("请输入身份证号码");
            return false;
        }
        if (! pwd01.equals(pwd02)){
            ToastUitl.showShort("两次密码不一致");
            return false;
        }
        AppContext.setProperty(Config.Name,name);
        AppContext.setProperty(Config.Phone,phone);
        return true;
    }
}
