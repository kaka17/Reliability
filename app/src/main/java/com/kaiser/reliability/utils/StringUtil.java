package com.kaiser.reliability.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangrongfang029 on 2016/11/8.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空或null
     *
     * @param input
     * @return
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)
                || "null".equals(input.toLowerCase(Locale.CHINA)))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < strLen; ++i) {
            if (!(Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
    /**
     * MD5加密
     *
     * @param s
     * @return
     */
    public static String get32MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            // 使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    //获取当前版本号
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
            if (!TextUtils.isEmpty(versionName)) {
                return versionName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 格式化json
     *
     * @param oldStr
     * @return
     */
    public static String dealJson(String oldStr) {
        if (oldStr.startsWith("null(")) {
            oldStr = oldStr.substring(("null(").length(), oldStr.length() - 1);
        }
        return oldStr;
    }



    /**
     * 获取渠道号
     *
     * @param context
     * @return
     */
    public static String getChannelName(Context context) {
        String channelName = "";
        if (context == null) {
            return channelName;
        }
        PackageManager pm = context.getPackageManager();
        if (pm != null) {
            try {
                ApplicationInfo info = pm.getApplicationInfo(context.getPackageName(), pm.GET_META_DATA);
                if (info != null) {
                    if (info.metaData != null) {
                        channelName = info.metaData.getString("TD_CHANNEL_ID");
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return channelName;

    }


    /**
     * 转字符编码“UTF-8”
     * @param str
     * @return
     */
    public static String StringUTF8(String str) {
        try {
            String urlUTF8 = URLDecoder.decode(str, "UTF-8");
            return urlUTF8;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除String里面的空格，特殊符号
     *
     * @param str
     * @return
     */
    public static String specialCharDel(String str) {
        String strSpecial = str.replaceAll(" ", "");
        strSpecial = strSpecial.replaceAll("<", "");
        strSpecial = strSpecial.replaceAll(">", "");
        strSpecial = strSpecial.replaceAll("‘", "");
        strSpecial = strSpecial.replaceAll("%", "");
        strSpecial = strSpecial.replaceAll("$", "");
        return strSpecial;
    }

    /**
     * 纯数字判断
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 判断字符串是否包含字母
     *
     * @param str
     * @return
     */
    public static boolean isContainLetters(String str) {
        for (char i = 'A'; i <= 'Z'; i++) {
            if (str.contains(String.valueOf(i))) {
                return true;
            }
        }
        for (char i = 'a'; i <= 'z'; i++) {
            if (str.contains(String.valueOf(i))) {
                return true;
            }
        }
        return false;
    }


}
