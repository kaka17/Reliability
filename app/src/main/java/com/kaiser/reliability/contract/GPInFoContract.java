package com.kaiser.reliability.contract;


import com.kaiser.reliability.base.BaseModel;
import com.kaiser.reliability.base.BasePresenter;
import com.kaiser.reliability.base.BaseView;
import com.kaiser.reliability.bean.GPInFo;
import com.kaiser.reliability.bean.GPInfoBean;

import java.util.List;

import rx.Observable;

//import rx.Observer;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface GPInFoContract {

    interface GPInfoModels extends BaseModel {
        Observable<GPInfoBean<List<GPInFo>>> getData(String gid, String 	key);
    }

    interface GPInfoViews extends BaseView {
        void showData(GPInfoBean<List<GPInFo>> data);
    }

    abstract static class GPInfoPresenters extends BasePresenter<GPInfoViews,GPInfoModels> {
        public abstract void getGpInfoData(String gid,String key);
    }

}
