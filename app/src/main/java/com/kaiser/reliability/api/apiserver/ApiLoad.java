package com.kaiser.reliability.api.apiserver;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaiser.reliability.api.ApiService;
import com.kaiser.reliability.api.LoadClient;
import com.kaiser.reliability.base.AppContext;
import com.kaiser.reliability.base.BaseApplication;
import com.kaiser.reliability.configs.Config;
import com.kaiser.reliability.utils.LogUtil;
import com.kaiser.reliability.utils.NetWorkUtils;
import com.kaiser.reliability.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ex-huangkeze001 on 2018/5/6.   信度
 */

public class ApiLoad {

    public Retrofit retrofit;
    public LoadClient service;
//    public static final String BASEPATH = "http://47.254.129.103:8080/kaka/user/";
    public static final String BASEPATH = "http://192.168.43.225:8080/kaka/user/";

    private static class SingletonHolder {
        private static final ApiLoad INSTANCE = new ApiLoad();
    }

    public static ApiLoad getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //增加头部信息
    Interceptor headerInterceptor =new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request build = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build();
            return chain.proceed(build);
        }
    };

    //构造方法私有
    private ApiLoad() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676, TimeUnit.MILLISECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();


        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASEPATH)
                .build();
        service = retrofit.create(LoadClient.class);

    }

    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Log.d("Okhttp", "no network");
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }

    public RequestBody jsdata(Map<String, Object> maps){
        try {
            JSONObject jsonObject=new JSONObject();
            JSONObject object=new JSONObject(maps);
            if (!StringUtil.isEmpty(AppContext.getProperty(Config.Phone))){
                jsonObject.put("mobile",AppContext.getProperty(Config.Phone));
            }else {
                jsonObject.put("mobile","");
            }
            if (!StringUtil.isEmpty(AppContext.getProperty(Config.OnlineId))){
                jsonObject.put("onlineId",AppContext.getProperty(Config.OnlineId));
            }else {
                jsonObject.put("onlineId","");
            }
            jsonObject.put("data",object);
            LogUtil.logE(jsonObject.toString());
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
            return requestBody;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
