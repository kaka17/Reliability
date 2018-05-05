package com.kaiser.reliability.base;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;

import com.kaiser.reliability.configs.Config;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * 描述：MultiDexApplication防止方法数过多
 * Created by qyh on 2016/12/6.
 */
public class BaseApplication extends MultiDexApplication {
    private static BaseApplication baseApplication;
    private static Context mContext;
    private static String PREF_NAME = Config.APP + ".pref";
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        mContext=getApplicationContext();
        //屏幕适配
        AutoLayoutConifg.getInstance().useDeviceSize();
        ZXingLibrary.initDisplayOpinion(this);
    }
    public static Context getAppContext() {
        return baseApplication;
    }

    public static void setProperty(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        apply(editor);
    }
    public static void setPropertyBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        apply(editor);
    }
    public static void clearPropertyData() {
        getPreferences().edit().clear().commit();
    }

    public static String getProperty(String key) {
        return getPreferences().getString(key, null);
    }
    public static boolean getPropertyBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }

    public static void removeProperty(String... keys) {
        for (String key : keys) {
            SharedPreferences.Editor editor = getPreferences().edit();
            editor.putString(key, null);
            apply(editor);
        }
    }
    public static void apply(SharedPreferences.Editor editor) {
        editor.apply();
    }
    public static SharedPreferences getPreferences() {
        return mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


}
