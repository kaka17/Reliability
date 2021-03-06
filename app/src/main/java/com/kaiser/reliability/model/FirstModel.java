package com.kaiser.reliability.model;

import com.kaiser.reliability.api.apiserver.Api;
import com.kaiser.reliability.baserx.RxSchedulers;
import com.kaiser.reliability.bean.FirstBean;
import com.kaiser.reliability.bean.GirlData;
import com.kaiser.reliability.contract.FirstContract;

import java.util.List;


import rx.Observable;
import rx.functions.Func1;

/**
 * 描述：
 * Created by qyh on 2016/12/28.
 */

public class FirstModel implements FirstContract.Model {
    @Override
    public Observable<List<FirstBean>> getListData(int size, int page) {

        return Api.getInstance().service.getListData(size,page)
                .map(new Func1<GirlData, List<FirstBean>>() {
            @Override
            public List<FirstBean> call(GirlData girlData) {
                return girlData.getResults();
            }
      }).compose(RxSchedulers.<List<FirstBean>>io_main());
    }

    private void gg(){
        Observable<GirlData> listData = Api.getInstance().service.getListData(11, 10);
        Observable<List<FirstBean>> compose = listData.map(new Func1<GirlData, List<FirstBean>>() {
            @Override
            public List<FirstBean> call(GirlData girlData) {
                List<FirstBean> results = girlData.getResults();
                return results;
            }
        }).compose(RxSchedulers.<List<FirstBean>>io_main());
    }
}
