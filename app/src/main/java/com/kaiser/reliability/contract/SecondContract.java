package com.kaiser.reliability.contract;

import com.kaiser.reliability.base.BaseModel;
import com.kaiser.reliability.base.BasePresenter;
import com.kaiser.reliability.base.BaseView;
import com.kaiser.reliability.bean.SeconData;
import com.kaiser.reliability.bean.SecondBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/8/14.
 */
public interface SecondContract {

    interface SecondModels extends BaseModel {
        //请求获取数据
        Observable<List<SecondBean>> getListData(String longitude, String  latitude, String pageid);
        Observable<SeconData> getList(String longitude, String  latitude,String pageid);
        Observable< SeconData > getListw(String longitude, String  latitude,String pageid);

    }

    interface SecondView extends BaseView{
        void showData( List<SecondBean> list);
        void showData(SeconData seconData);
        void showDatas(SeconData conData,boolean isRefresh);
    }

    abstract static class SecondPresenters extends BasePresenter<SecondView,SecondModels> {
        public abstract void getLockList(String longitude,String latitude,String pageid,boolean isRefresh);
    }

}
