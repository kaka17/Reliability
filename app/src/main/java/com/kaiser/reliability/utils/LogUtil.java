package com.kaiser.reliability.utils;

import android.util.Log;

import com.kaiser.reliability.configs.Config;


public class LogUtil {
	public static boolean DEBUG = true;

	public LogUtil() {
	}

	public static final void analytics(String log) {
		if (DEBUG)
			Log.d(Config.APP, log);
	}

	public static final void error(String log) {
		if (DEBUG)
			Log.e(Config.APP, "" + log);
	}

	public static final void log(String log) {
		if (DEBUG)
			Log.i(Config.APP, log);
	}

	public static final void log(String tag, String log) {
		if (DEBUG)
			Log.i(tag, log);
	}

	public static final void logv(String log) {
		if (DEBUG)
			Log.v(Config.APP, log);
	}

	public static final void warn(String log) {
		if (DEBUG)
			Log.w(Config.APP, log);
	}

	public static final void loge(String tag, String log){
		if (DEBUG){
			Log.e(tag, log);
		}
	}
	public static final void logE(String log){
		if (DEBUG){
			Log.e("TAG", "------->"+log);
		}
	}
}
