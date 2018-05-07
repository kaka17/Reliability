package com.kaiser.reliability.model;

import com.kaiser.reliability.api.apiserver.HttpClient;
import com.kaiser.reliability.baserx.RxSchedulers;
import com.kaiser.reliability.bean.GPInFo;
import com.kaiser.reliability.bean.GPInfoBean;
import com.kaiser.reliability.contract.GPInFoContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/8/17.
 */
public class GPInfoModel implements GPInFoContract.GPInfoModels {

    @Override
    public Observable<GPInfoBean<List<GPInFo>>> getData(String gid, String key) {
        Map<String, String> map=new HashMap<>();
        map.put("key",key);
        map.put("gid", gid);
//        map.put("type", "0");

        Observable<GPInfoBean<List<GPInFo>>> gpInfo = HttpClient.instance().service.getGPInfo(map);
        Observable<GPInfoBean<List<GPInFo>>> compose = gpInfo.map(new Func1<GPInfoBean<List<GPInFo>>, GPInfoBean<List<GPInFo>>>() {
            @Override
            public GPInfoBean<List<GPInFo>> call(GPInfoBean<List<GPInFo>> listGPInfoBean) {
                return listGPInfoBean;
            }
        }).compose(RxSchedulers.<GPInfoBean<List<GPInFo>>>io_main());

        return compose;
    }
}
