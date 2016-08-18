package com.bwf.framwork.http;


import com.zhy.http.okhttp.OkHttpUtils;


/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description:
 */
public class HttpHelper {


    public static void getDetail(String url,String pageNo,String pageSize,HttpCallBack callBack){
        OkHttpUtils
                .post()
                .url(url)
                .addParams("pageNo", pageNo)
                .addParams("pageSize", pageSize)
                .build()
                .execute(callBack);
    }

    //得到城市数据
    public static void getCityData(String url,HttpCallBack callBack){
        OkHttpUtils
                .post()
                .url(url)
                .addParams("pageSize", "4")
                .build()
                .execute(callBack);
    }

    //用于定位城市
    public static void getLocationCityData(String url,String longitude,String latitude,HttpCallBack callBack){
        OkHttpUtils
                .post()
                .url(url)
                .addParams("longitude", longitude)
                .addParams("latitude",latitude)
                .build()
                .execute(callBack);
    }
}
