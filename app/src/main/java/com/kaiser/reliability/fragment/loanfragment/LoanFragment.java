package com.kaiser.reliability.fragment.loanfragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.kaiser.reliability.R;
import com.kaiser.reliability.activity.LoginActivity;
import com.kaiser.reliability.activity.MyInfoActivity;
import com.kaiser.reliability.activity.RegistActivity;
import com.kaiser.reliability.adapter.AdvertisementAdapter;
import com.kaiser.reliability.adapter.LoanAdapter;
import com.kaiser.reliability.base.AppContext;
import com.kaiser.reliability.base.BaseFragment;
import com.kaiser.reliability.base.baseadapter.BaseQuickAdapter;
import com.kaiser.reliability.base.baseadapter.OnItemClickListener;
import com.kaiser.reliability.bean.loanbean.LoanBean;
import com.kaiser.reliability.configs.Config;
import com.kaiser.reliability.utils.StringUtil;
import com.kaiser.reliability.view.NoDataPop;
import com.kaiser.reliability.view.TakeLoginPop;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by ex-huangkeze001 on 2018/4/19.
 */

public class LoanFragment extends BaseFragment {
    private Context mContext;
    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.teLayou)
    RelativeLayout teLayou;
    ViewPager mPager;
    private  LinearLayoutManager manager;
    private LoanAdapter  adapter;
    private List<LoanBean> list=new ArrayList<>();
//    @BindView(R.id.tvLead)
    TextView tvLead;
    //
    private int mTextViewHeight;
    private RecyclerViewHeader mRecyclerViewHeader;
    //头部图片(轮播图的高度)
    private int mRecyclerHeaderBannerHeight;
    //头部的高度
    private int mRecyclerHeaderHeight;
    int recyclerItemHeight;
    private TakeLoginPop takeLoginPop;
    private NoDataPop noDataPop;
    private AdvertisementAdapter mPagerAdapter;
    private List<View> views=new ArrayList<>();
    private View ivDot01,ivDot02,ivDot03;
    private int pos=0;
    @Override
    protected int getLayoutResource() {
        mContext=getActivity();
        return R.layout.loan_fragment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        getData();
        adapter=new LoanAdapter(R.layout.loan_item,list);
        manager = new LinearLayoutManager(mContext);
        rvList.setLayoutManager(manager);
        rvList.setHasFixedSize(true);
        //设置适配器加载动画
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        adapter.isFirstOnly(false);
        rvList.setAdapter(adapter);

        //获取到文本的高度
        mTextViewHeight = teLayou.getHeight();

        //轮播图片的高度--和xml图片的高度是一样的
        mRecyclerHeaderBannerHeight = (int) 800;
        //RecyclerView每个Item之间的距离,和Adapter中设置的距离一样
        recyclerItemHeight = (int) 600;

        //添加头部视图,其布局文件就忽略
        mRecyclerViewHeader = RecyclerViewHeader.fromXml(mContext, R.layout.loan_head);

//将头部视图添加到RecyclerView中
        mRecyclerViewHeader.attachTo(rvList);
        //第一次进来其状态显示
        mRecyclerViewHeader.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerHeaderHeight =  mRecyclerViewHeader.getHeight();
                mTextViewHeight = teLayou.getHeight();
                teLayou.setVisibility(View.VISIBLE);
                teLayou.setAlpha(0);
            }
        });
        //
        showSystemParameter();
        tvLead= (TextView) mRecyclerViewHeader.findViewById(R.id.tvLead);
//        tvLead= (TextView) getActivity().findViewById(R.id.tvLead);
        tvLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isEmpty(AppContext.getProperty(Config.Phone))){
                    showPop(v);
                }else {
                    showChangInfoPop(v);
                }
            }
        });
        getHeadView();
        getFooterView();

    }
    private void getFooterView(){
       View view= LayoutInflater.from(getActivity()).inflate(R.layout.footer_view,null);
        adapter.addFooterView(view);
    }
    private void getHeadView(){
        View view01= LayoutInflater.from(getActivity()).inflate(R.layout.head_item01,null);
        View view02= LayoutInflater.from(getActivity()).inflate(R.layout.head_item02,null);
        View view03= LayoutInflater.from(getActivity()).inflate(R.layout.head_item03,null);
        views.add(view01);
        views.add(view02);
        views.add(view03);

        mPager= (ViewPager) mRecyclerViewHeader.findViewById(R.id.mPager);
       ivDot01=  mRecyclerViewHeader.findViewById(R.id.ivDot01);
       ivDot02=  mRecyclerViewHeader.findViewById(R.id.ivDot02);
       ivDot03=  mRecyclerViewHeader.findViewById(R.id.ivDot03);
        mPagerAdapter=new AdvertisementAdapter(getActivity(),views);
        mPager.setAdapter(mPagerAdapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        ivDot01.setBackgroundResource(R.color.whiles);
                        ivDot02.setBackgroundResource(R.color.grgray);
                        ivDot03.setBackgroundResource(R.color.grgray);
                        break;
                    case 1:
                        ivDot01.setBackgroundResource(R.color.grgray);
                        ivDot02.setBackgroundResource(R.color.whiles);
                        ivDot03.setBackgroundResource(R.color.grgray);
                        break;
                    case 2:
                        ivDot01.setBackgroundResource(R.color.grgray);
                        ivDot02.setBackgroundResource(R.color.grgray);
                        ivDot03.setBackgroundResource(R.color.whiles);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void getData(){
//        LoanBean bean1= new LoanBean(1+"","适合大学生",500+"","学生证");
//        LoanBean bean2= new LoanBean(2+"","普通民工",1000+"","无");
        LoanBean bean3= new LoanBean(1+"","适合上班族",1500+"","公积金+企业邮箱");
        LoanBean bean4= new LoanBean(2+"","白领／蓝领",2000+"","淘宝认证");
        LoanBean bean5= new LoanBean(3+"","公司经理",3000+"","征信报告");
//        list.add(bean1);
//        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
    }
    @Override
    protected void setMyOnClickListener() {
        rvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringUtil.isEmpty(AppContext.getProperty(Config.Phone))){
                    showPop(view);
                }else {
                    showChangInfoPop(view);
                }
            }
        });


        //设置其滑动事件
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //设置其透明度
                float alpha = 0;
                int scollYHeight = getScollYHeight(true, mRecyclerHeaderHeight);
                //起始截止变化高度,如可以变化高度为mRecyclerHeaderHeight
                int baseHeight = mRecyclerHeaderBannerHeight - recyclerItemHeight - mTextViewHeight;
                if(scollYHeight >= baseHeight) {
                    //完全不透明
                    alpha = 1;
                }else {
                    //产生渐变效果
                    alpha = scollYHeight / (baseHeight*1.0f);
                }
                teLayou.setAlpha(alpha);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
    /**
     * 计算RecyclerView滑动的距离
     * @param hasHead 是否有头部
     * @param headerHeight RecyclerView的头部高度
     * @return 滑动的距离
     */
    private int getScollYHeight(boolean hasHead, int headerHeight) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rvList.getLayoutManager();
        //获取到第一个可见的position,其添加的头部不算其position当中
        int position = layoutManager.findFirstVisibleItemPosition();
        //通过position获取其管理器中的视图
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        //获取自身的高度
        int itemHeight = firstVisiableChildView.getHeight();
        //有头部
        if(hasHead) {
            return headerHeight + itemHeight*position - firstVisiableChildView.getTop();
        }else {
            return itemHeight*position - firstVisiableChildView.getTop();
        }
    }
    private void showSystemParameter() {
        String TAG = "TAG：";
        Log.e(TAG, "手机厂商：" + getDeviceBrand());
        Log.e(TAG, "手机型号：" + getSystemModel());
        Log.e(TAG, "Android系统版本号：" + getSystemVersion());
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }


    private void showPop(View view){
        if (takeLoginPop==null|| ! takeLoginPop.isShowing()){
            takeLoginPop=new TakeLoginPop(mContext, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.tvLogin:
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            break;
                        case R.id.tvRegist:
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
                            startActivity(new Intent(getActivity(),MyInfoActivity.class));
                            takeLoginPop.dismiss();
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
            m_musictask.schedule(playTimer, 3000,3000);
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
