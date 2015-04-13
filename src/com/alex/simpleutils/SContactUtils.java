package com.alex.simpleutils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

/**
 * 联系人相关操作
 * @author caisenchuan
 */
public class SContactUtils {
    private static final String TAG = null;

    /**
     * 拨打电话
     */
    public static void call(Context context, String phone) {
        SLog.d(TAG, "call : " + phone);
        
        if(!TextUtils.isEmpty(phone)) {
            //用intent启动拨打电话  
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);  
        }
    }
    
    /**
     * 根据电话号码取得联系人姓名
     */
    public static String getContactNameByPhoneNumber(Context context, String phone) {
        String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME,
                                ContactsContract.CommonDataKinds.Phone.NUMBER };

        Cursor cursor = context.getContentResolver().query (
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,     // Which table
                projection, // Which columns to return.
                ContactsContract.CommonDataKinds.Phone.NUMBER + " = '" + phone + "'", // WHERE clause.
                null, // WHERE clause value substitution
                null); // Sort order.

        if (cursor == null) {
            SLog.d(TAG, "getPeople null");
            return null;
        }
        
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            // 取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String name = cursor.getString(nameFieldColumnIndex);
            
            return name;
        }
        
        return null;
    }
    
    /**
     * 根据联系人姓名取得电话号码
     */
    public static String getPhoneNumberByContactName(Context context, String name) {
        String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME,
                                ContactsContract.CommonDataKinds.Phone.NUMBER };

        Cursor cursor = context.getContentResolver().query (
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,     // Which table
                projection, // Which columns to return.
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = '" + name + "'", // WHERE clause.
                null, // WHERE clause value substitution
                null); // Sort order.

        if (cursor == null) {
            SLog.d(TAG, "getPeople null");
            return null;
        }
        
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String phone = cursor.getString(nameFieldColumnIndex);
            
            SLog.d(TAG, "get phone : " + phone);
            
            return phone;
        }
        
        return null;
    }
}
