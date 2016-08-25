package com.bwf.tuanche.ui.login;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ma on 2016/8/23.
 */
public class SmsObserver extends ContentObserver {

    private String CodeText;
    private EditText CodeEd;

    private Context context;

    public SmsObserver(Context context,Handler handler) {
        super(handler);
        this.context = context;
    }

    @Override
    public void onChange(boolean selfChange) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        Cursor cursor = context.getContentResolver().query(
                Uri.parse("content://sms/inbox"), null, null, null, null);
        //这里不要使用while循环.我们只需要获取当前发送过来的短信数据就可以了.
        cursor.moveToNext();
        sb.append("body=" + cursor.getString(cursor.getColumnIndex("body"))); //获取短信内容的实体数据.
        Pattern pattern = Pattern.compile("[^0-9]"); //正则表达式.
        Matcher matcher = pattern.matcher(sb.toString());
        CodeText = matcher.replaceAll("");
        CodeEd.setText(CodeText); //将输入验证码的控件内容进行改变.
        cursor.close(); //关闭游标指针.
        super.onChange(selfChange);
    }
}
