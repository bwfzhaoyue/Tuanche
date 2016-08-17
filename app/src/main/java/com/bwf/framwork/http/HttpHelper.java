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

    /**
     * 热门品牌
     * @param cityId
     */
    public static void getHotCar(String cityId,HttpCallBack callBack){
        OkHttpUtils.post().url(UrlUtils.HOT_CAR_TYPE)
                .addParams("isBuy","2")
                .addParams("cityId",cityId)
                .build()
                .execute(callBack);
    }

    /**
     * 选车列表
     * @param cityId
     */
    public static void getCarTypeList(String cityId,HttpCallBack callBack){
        OkHttpUtils.post().url(UrlUtils.CAR_TYPE_LIST)
                .addParams("cityId",cityId)
                .build()
                .execute(callBack);
    }

    /**
     * 根据条件选车模块中的选车条件
     */
    public static void getConditionToChoose(HttpCallBack callBack){
        OkHttpUtils.post().url(UrlUtils.CHOOSE_CAR_BY_CONDITION)
                .build()
                .execute(callBack);
    }


}
