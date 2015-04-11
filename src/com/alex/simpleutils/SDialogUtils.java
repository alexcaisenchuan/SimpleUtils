package com.alex.simpleutils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 一些常用的Dialog显示工具类
 * @author caisenchuan
 */
public class SDialogUtils {
    /*--------------------------
     * 常量
     *-------------------------*/

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
     * 显示一个带有确认按钮的对话框
     * @param context 上下文
     * @param title 提示文字
     */
    public static void showOKButtonDialog(Context context, String title) {
        showOKButtonDialog(context, title, null);
    }
    
    /**
     * 显示一个带有确认按钮的对话框
     * @param context 上下文
     * @param title 提示文字
     * @param ok_listener 点击确定后执行的函数
     * @author lenovo
     */
    public static void showOKButtonDialog(Context context, String title, AlertDialog.OnClickListener ok_listener) {
        AlertDialog dlg = new AlertDialog.Builder(context).create();
        dlg.setTitle(title);
        dlg.setButton(Dialog.BUTTON_NEGATIVE, context.getString(R.string.ok), ok_listener);
        dlg.show();
    }
    
    /**
     * 显示一个带有确认，取消按钮的对话框
     * @param context 上下文
     * @param title 提示文字
     * @param ok_listener 点击确定后执行的函数
     * @author lenovo
     */
    public static void showOKCancelButtonDialog(Context context, String title, AlertDialog.OnClickListener ok_listener) {
        AlertDialog dlg = new AlertDialog.Builder(context).create();
        dlg.setTitle(title);
        dlg.setButton(Dialog.BUTTON_NEGATIVE, context.getString(R.string.ok), ok_listener);
        dlg.setButton(Dialog.BUTTON_POSITIVE, context.getString(R.string.cancel), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //...
            }
        });
        dlg.show();
    }
    
    /**
     * 创建不可取消的进度提示框
     * @param context
     * @param title
     * @return
     */
    public static Dialog buildSpinnerProgressDialog(Context context, String title) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(title);
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        return dialog;
    }
    
    /*--------------------------
     * protected、packet方法
     *-------------------------*/

    /*--------------------------
     * private方法
     *-------------------------*/

}
