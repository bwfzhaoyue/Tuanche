package com.bwf.framwork.http;


import com.bwf.framwork.utils.UrlUtils;
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

    public static void getTopBrand(String cityId,HttpCallBack callBack){
        OkHttpUtils.get().url(UrlUtils.TOPBRAND).addParams("cityId",cityId).build().execute(callBack);
    }

    public static void getMainBanner(String cityId,HttpCallBack callBack){
        OkHttpUtils.post().url(UrlUtils.MAIN_BANNER_URL).addParams("cityId",cityId).build().execute(callBack);


    }
    public static void getHotBrand(String cityId,String isBuy,HttpCallBack callBack){
        OkHttpUtils.post().url(UrlUtils.HOT_BRAND_URL).addParams("cityId",cityId).addParams("isBuy",isBuy).build().execute(callBack);


    }

    public static void getHotModle(String cityId,String count,String offset,HttpCallBack callBack){
        OkHttpUtils.post().url(UrlUtils.HOT_MODLE_URL).addParams("cityId",cityId).addParams("count",count).addParams("offset",offset).build().execute(callBack);


    }

}
