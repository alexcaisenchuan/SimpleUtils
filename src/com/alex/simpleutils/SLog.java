package com.alex.simpleutils;

import android.util.Log;

/**
 * 打印调试
 * @author caisenchuan
 */
public class SLog {
    /*--------------------------
     * 自定义类型
     *-------------------------*/
    
    /*--------------------------
     * 常量
     *-------------------------*/

    /*--------------------------
     * 成员变量
     *-------------------------*/

    /*--------------------------
     * public方法
     *-------------------------*/
    ////////////////普通字符串//////////////////
    public static void v(String tag, String str) {
        Log.v(tag, str);
    }
    
    public static void d(String tag, String str) {
        Log.d(tag, str);
    }
    
    public static void w(String tag, String str) {
        Log.w(tag, str);
    }
    
    public static void e(String tag, String str) {
        Log.e(tag, str);
    }
    
    ////////////////支持格式化参数//////////////////
    public static void v(String tag, String format, Object... args) {
        Log.v(tag, String.format(format, args));
    }
    
    public static void d(String tag, String format, Object... args) {
        Log.d(tag, String.format(format, args));
    }
    
    public static void w(String tag, String format, Object... args) {
        Log.w(tag, String.format(format, args));
    }
    
    public static void e(String tag, String format, Object... args) {
        Log.e(tag, String.format(format, args));
    }
    
    ////////////////支持堆栈输出//////////////////
    public static void d(String tag, String str, Throwable tr) {
        Log.d(tag, str, tr);
    }
    
    public static void w(String tag, String str, Throwable tr) {
        Log.w(tag, str, tr);
    }
    
    public static void e(String tag, String str, Throwable tr) {
        Log.e(tag, str, tr);
    }
    
    ////////////////看回调栈专用//////////////////
    public static void showTrace(String tag, String str) {
        Log.i(tag, str);
        Throwable th = new Throwable();
        th.printStackTrace();
    }
    
    /*--------------------------
     * protected、packet方法
     *-------------------------*/

    /*--------------------------
     * private方法
     *-------------------------*/

}
