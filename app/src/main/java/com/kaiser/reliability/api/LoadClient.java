package com.kaiser.reliability.api;
import com.kaiser.reliability.bean.BaseBean;
import com.kaiser.reliability.load.bena.Users;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ex-huangkeze001 on 2018/5/6.
 */

public interface LoadClient {

    String REGIST="regist";
    String Login="login";
    String UserInfo="userInfo";
    String UserStatus="applyStatus";
    String UserUpData="updatePersonInfo";


//    @FormUrlEncoded
    @POST(REGIST)
    Observable<BaseBean<Users>> getRegistUser(@Body RequestBody requestBody);

    @POST(Login)
    Observable<BaseBean<Users>> getLoginUser(@Body RequestBody requestBody);

    @POST(UserInfo)
    Observable<BaseBean<Users>> getUserInfo(@Body RequestBody requestBody);

    @POST(UserStatus)
    Observable<BaseBean<Users>> getUserStatus(@Body RequestBody requestBody);

    @POST(UserUpData)
    Observable<BaseBean<Users>> getUpUsers(@Body RequestBody requestBody);



}
