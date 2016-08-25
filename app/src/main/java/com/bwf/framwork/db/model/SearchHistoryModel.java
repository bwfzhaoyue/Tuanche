package com.bwf.framwork.db.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.bwf.framwork.base.BaseModel;
import com.bwf.tuanche.ui.mainpager.entity.SearchHistoryBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zengqiang on 2016/8/25.
 * Description:Tuanche
 */
public class SearchHistoryModel extends BaseModel {
    public static final String TABLENAME = "search_history";

    private static Map<String, String> paramsMap = new HashMap<>();

    public static SearchHistoryModel userModel;

    public SearchHistoryModel() {

    }


    public static SearchHistoryModel getUserModel() {
        if (userModel == null) {
            userModel = new SearchHistoryModel();
        }
        return userModel;
    }


    /**
     * 静态代码块，为paramsMap赋值
     */
    static {
        paramsMap.put(_ID, "INTEGER PRIMARY KEY AUTOINCREMENT");//主键，且自动增长
        paramsMap.put("date", "TEXT NOT NULL");//列名称1
        paramsMap.put("content", "TEXT NOT NULL");//列名称2
    }

    //获得当前表格名称
    protected String getTableName() {
        return TABLENAME;
    }

    //获得当前表格行列族名称集合
    protected Map<String, String> getParamsMap() {
        return paramsMap;
    }

    /**
     * 往表格里添加一条数据
     */

    public void insertUser(SearchHistoryBean history) {
        if (history == null)
            return;
        SearchHistoryBean result = queryUserByName(history.content);
        if (result!=null) {
            deleteOne(result);
        }
        ContentValues values = new ContentValues();
        values.put("date",history.date);
        values.put("content", history.content);
        insert(values);
    }

    /**
     * 更新一条数据
     */
    public void updataUser(SearchHistoryBean history) {
        if (history == null)
            return;
        ContentValues values = new ContentValues();
        values.put("date",history.date);
        values.put("content", history.content);
        update(values, "content=?", new String[]{history.content});
    }

    /**
     * 查询用户
     */

    public SearchHistoryBean queryUserByName(String id) {
        SearchHistoryBean history = new SearchHistoryBean();
        String sql = "select * from " + TABLENAME + " where content='" + id + "'";
        Cursor cursor = query(sql);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                history.date = cursor.getString(cursor.getColumnIndex("date"));
                history.content = cursor.getString(cursor.getColumnIndex("content"));
            }

        }
        return history;
    }



    /**
     * 查询所有用户
     */
    public List<SearchHistoryBean> queryAllCustomer() {

        Cursor cursor = queryAll();
        List<SearchHistoryBean> customers = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                SearchHistoryBean customer = new SearchHistoryBean();
                customer.date = cursor.getString(cursor.getColumnIndex("date"));
                customer.content = cursor.getString(cursor.getColumnIndex("content"));
                customers.add(customer);
            }
            cursor.close();
        }

        return customers;
    }

    /**
     * 删除一条数据
     */

    public void deleteOne(SearchHistoryBean customer) {
        delete("content=?", new String[]{customer.content});
    }
}
