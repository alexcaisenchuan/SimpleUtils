package com.alex.simpleutils;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * 设备工具类
 * @author caisenchuan
 */
public class SDeviceUtils {
    /*--------------------------
     * 常量
     *-------------------------*/
    private static final String TAG = SDeviceUtils.class.getSimpleName();
    
    /*--------------------------
     * 自定义类型
     *-------------------------*/

    /*--------------------------
     * 成员变量
     *-------------------------*/

    /*--------------------------
     * public方法
     *-------------------------*/
    /**
     * 获取系统版本
     */
    public static int getAndroidSDKVersion() { 
        return android.os.Build.VERSION.SDK_INT; 
     }

    /**
     * 判断系统是否安卓4.4以下版本
     * @return
     */
    public static boolean underAndroid44() {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 判断系统是否安卓4.3以下版本
     * @return
     */
    public static boolean underAndroid43() {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 友盟集成测试使用的获取设备信息的函数
     * @param context
     * @return
     */
    public static String umengGetDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(
                        context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * 根据应用名字启动应用
     * @param context
     * @param name
     * @param notExact 若为true，则是模糊查找
     * @return 是否找到应用
     */
    public static boolean startAppByName(Context context, String name, boolean notExact) {
        boolean ret = false;
        PackageManager packageManager = context.getPackageManager();
        
        // 获取手机里的应用列表
        List<PackageInfo> pInfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pInfo.size(); i++) {
            PackageInfo p = pInfo.get(i);
            // 获取相关包的<application>中的label信息，也就是-->应用程序的名字 
            String label = packageManager.getApplicationLabel(p.applicationInfo).toString();
            SLog.d(TAG, "Try label : %s", label);
            boolean equals = false;
            if(notExact) {
                if(label.equalsIgnoreCase(name)) {
                    equals = true;
                }
            } else {
                if(label.equals(name)) {
                    equals = true;
                }
            }
            
            if (equals) {
                //比较label  
                String pName = p.packageName;
                //获取包名  
                Intent intent = new Intent();
                //获取intent  
                intent = packageManager.getLaunchIntentForPackage(pName); 
                context.startActivity(intent);
                ret = true;
                break;
            }
        }
        
        return ret;
    }
    /*--------------------------
     * protected、packet方法
     *-------------------------*/

    /*--------------------------
     * private方法
     *-------------------------*/

}
