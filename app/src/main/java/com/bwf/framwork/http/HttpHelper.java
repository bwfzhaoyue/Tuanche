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

}
