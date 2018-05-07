package com.kaiser.reliability.activity;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.kaiser.reliability.R;
import com.kaiser.reliability.api.apiserver.ApiLoad;
import com.kaiser.reliability.base.BaseActivity;
import com.kaiser.reliability.baserx.RxManager;
import com.kaiser.reliability.baserx.RxSchedulers;
import com.kaiser.reliability.baserx.RxSubscriber;
import com.kaiser.reliability.bean.BaseBean;
import com.kaiser.reliability.bean.TabEntity;
import com.kaiser.reliability.fragment.loanfragment.LoanFragment;
import com.kaiser.reliability.fragment.loanfragment.MineFragment;
import com.kaiser.reliability.load.bena.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

public class ToLoanActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    CommonTabLayout tabLayout;
    private FrameLayout fl_body;
    private String[] mTitles={"借贷","我的"};
    private int[] mIconUnselectIds={R.mipmap.icon_mine_default, R.mipmap.icon_mine_default};
    private int[] mIconSelectIds={R.mipmap.icon_home_selected, R.mipmap.icon_mine_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private LoanFragment LoanFragment;
    private MineFragment mineFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_to_loan;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        fl_body = (FrameLayout) findViewById(R.id.fl_body);
        initTab();

    }

    @Override
    public void setOnClicks() {

    }

    @Override
    public void initData() {
        getuserStatus();
    }

    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }
    //初始化碎片
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition=0;
        if(null==savedInstanceState){
            LoanFragment = new LoanFragment();
            mineFragment = new MineFragment();

            fragmentTransaction.add(R.id.fl_body,LoanFragment,"LoanFragment");
            fragmentTransaction.add(R.id.fl_body,mineFragment,"mineFragment");
        }else{
            LoanFragment= (LoanFragment) getSupportFragmentManager().findFragmentByTag("LoanFragment");
            mineFragment= (MineFragment) getSupportFragmentManager().findFragmentByTag("mineFragment");
        }
        fragmentTransaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (position){
            case 0:
                transaction.show(LoanFragment);
                transaction.hide(mineFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.show(mineFragment);
                transaction.hide(LoanFragment);
                transaction.commitAllowingStateLoss();
                break;

        }
    }

    private void getuserStatus(){
        Map<String,Object> map=new HashMap<>();
        RequestBody jsdata = ApiLoad.getInstance().jsdata(map);

        Observable<BaseBean<Users>> registUser = ApiLoad.getInstance().service.getUserStatus(jsdata);

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
                    Log.e("TAG","------"+user.toString()+"b=="+user.getData().getOnlineId());
                    if (user.isOk()){
//                        AppContext.setProperty(Config.Name,name);
//                        AppContext.setProperty(Config.Phone,phone);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }


}
