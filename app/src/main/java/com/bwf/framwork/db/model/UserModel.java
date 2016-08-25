package com.bwf.framwork.db.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.bwf.framwork.base.BaseModel;
import com.bwf.tuanche.ui.mainpager.entity.hotmodle.HotModleResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zengqiang on 2016/8/8.
 * Description:MySqlLiteDemo
 */
public class UserModel extends BaseModel {

    public static final String TABLENAME = "scan_history";

    private static Map<String, String> paramsMap = new HashMap<>();

    public static UserModel userModel;

    public UserModel() {

    }


    public static UserModel getUserModel() {
        if (userModel == null) {
            userModel = new UserModel();
        }
        return userModel;
    }


    /**
     * 静态代码块，为paramsMap赋值
     */
    static {
        paramsMap.put(_ID, "INTEGER PRIMARY KEY AUTOINCREMENT");//主键，且自动增长
        paramsMap.put("id", "TEXT NOT NULL");//列名称1
        paramsMap.put("brandId", "TEXT NOT NULL");//列名称2
        paramsMap.put("logo", "TEXT NOT NULL");//列名称3
        paramsMap.put("styleName", "TEXT NOT NULL");//列名称4
        paramsMap.put("factoryPrice", "TEXT NOT NULL");//列名称5
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

    public void insertUser(HotModleResult customer) {
        if (customer == null)
            return;
        HotModleResult result = queryUserByName(customer.id);
        if (result!=null) {
            deleteOne(result);
        }
        ContentValues values = new ContentValues();
        values.put("id", customer.id);
        values.put("brandId", customer.brandId);
        values.put("logo", customer.logo);
        values.put("styleName", customer.styleName);
        values.put("factoryPrice", customer.factoryPrice);
        insert(values);
    }

    /**
     * 更新一条数据
     */
    public void updataUser(HotModleResult customer) {
        if (customer == null)
            return;
        ContentValues values = new ContentValues();
        values.put("id", customer.id);
        values.put("brandId", customer.brandId);
        values.put("logo", customer.logo);
        values.put("styleName", customer.styleName);
        values.put("factoryPrice", customer.factoryPrice);
        update(values, "id=?", new String[]{customer.id});
    }

    /**
     * 查询用户
     */

    public HotModleResult queryUserByName(String id) {
        HotModleResult customer = new HotModleResult();
        String sql = "select * from " + TABLENAME + " where id='" + id + "'";
        Cursor cursor = query(sql);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                customer.id = cursor.getString(cursor.getColumnIndex("id"));
                customer.brandId = cursor.getString(cursor.getColumnIndex("brandId"));
                customer.logo = cursor.getString(cursor.getColumnIndex("logo"));
                customer.styleName = cursor.getString(cursor.getColumnIndex("styleName"));
                customer.factoryPrice = cursor.getString(cursor.getColumnIndex("factoryPrice"));
            }

        }
        return customer;
    }



    /**
     * 查询所有用户
     */
    public List<HotModleResult> queryAllCustomer() {

        Cursor cursor = queryAll();
        List<HotModleResult> customers = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                HotModleResult customer = new HotModleResult();
                customer.id = cursor.getString(cursor.getColumnIndex("id"));
                customer.brandId = cursor.getString(cursor.getColumnIndex("brandId"));
                customer.logo = cursor.getString(cursor.getColumnIndex("logo"));
                customer.styleName = cursor.getString(cursor.getColumnIndex("styleName"));
                customer.factoryPrice = cursor.getString(cursor.getColumnIndex("factoryPrice"));
                customers.add(customer);
            }
            cursor.close();
        }

        return customers;
    }

    /**
     * 删除一条数据
     */

    public void deleteOne(HotModleResult customer) {
        delete("id=?", new String[]{customer.id});
    }
}
