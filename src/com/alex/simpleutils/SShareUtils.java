package com.alex.simpleutils;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 分享工具类
 * @author caisenchuan
 */
public class SShareUtils {
    /*--------------------------
     * 常量
     *-------------------------*/

    /*--------------------------
     * 自定义数据类型
     *-------------------------*/

    /*--------------------------
     * 属性
     *-------------------------*/

    /*--------------------------
     * public方法
     *-------------------------*/
    /**
     * 进行分享
     * @param context
     * @param msgTitle
     * @param msgText
     * @param imgPath
     */
    public static void share(Context context,
                             String msgTitle,
                             String msgText,
                             String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        
        if (TextUtils.isEmpty(imgPath)) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/*");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        
        String activityTitle = "Share";
        
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
        context.startActivity(Intent.createChooser(intent, activityTitle));
    }
    
    /*--------------------------
     * protected方法
     *-------------------------*/

    /*--------------------------
     * private方法
     *-------------------------*/
}
