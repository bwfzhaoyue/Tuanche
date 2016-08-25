package com.bwf.framwork.http;


import com.bwf.framwork.utils.LogUtils;
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

    //得到城市数据
    public static void getCityData(String url,HttpCallBack callBack){
        OkHttpUtils
                .post()
                .url(url)
                .addParams("pageSize", "4")
                .build()
                .execute(callBack);
    }
    public static void getTopBrand(String cityId,HttpCallBack callBack){
        OkHttpUtils.get().url(UrlUtils.TOPBRAND).addParams("cityId",cityId).build().execute(callBack);
    }

    public static void getMainBanner(String cityId,HttpCallBack callBack){
        OkHttpUtils.get().url(UrlUtils.MAIN_BANNER_URL).addParams("cityId",cityId).build().execute(callBack);


    }
    public static void getHotBrand(String cityId,String isBuy,HttpCallBack callBack){
        OkHttpUtils.get().url(UrlUtils.HOT_BRAND_URL).addParams("cityId",cityId).addParams("isBuy",isBuy).build().execute(callBack);


    }

    public static void getHotModle(String cityId,String count,String offset,HttpCallBack callBack){
        OkHttpUtils.get().url(UrlUtils.HOT_MODLE_URL).addParams("cityId",cityId).addParams("count",count).addParams("offset",offset).build().execute(callBack);


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

    /**
     * 从热门品牌到详情
     * @param firmbrandId
     * @param cityId
     * @param callBack
     */
    public static void getCarDetailFromHotBand(String firmbrandId,String cityId,HttpCallBack callBack){
        OkHttpUtils.post().url(UrlUtils.CAR_DETAILS_URL).addParams("firmbrandId",firmbrandId).addParams("cityId",cityId).build().execute(callBack);
    }

    /**
     * 从热门车型到详情
     */
    public static void getCarDetailFromHotModle(String  styleId,String brandId,String cityId,HttpCallBack callBack){

        OkHttpUtils.get().url(UrlUtils.CAR_DETAILS_URL).addParams("cityId",cityId).addParams("styleId", styleId).addParams("brandId",brandId).build().execute(callBack);
        LogUtils.e(UrlUtils.CAR_DETAILS_URL);
    }

    /**
     * 婚姻座驾
     */

    public static void getMarrigeCar(HttpCallBack callBack){
        OkHttpUtils.get().url(UrlUtils.MARRIGE_CAR_URL).build().execute(callBack);
    }

    /**
     * 全部评价
     */
    public static void getAllEvalute(String count,String offset, String cityId,String brandId,HttpCallBack callBack){
        OkHttpUtils.get().url(UrlUtils.All_EVALUTE_URL).
                addParams("count",count).addParams("offset",offset).addParams("cityId",cityId)
        .addParams("brandId",brandId).build().execute(callBack);
    }

     /**
      *  根据车品牌获取车列表
     */
    public static void getCarListByBrand(String type,String cityId,String brandId,HttpCallBack callBack){
        OkHttpUtils.post().url(UrlUtils.CAR_LIST_BY_BRAND)
                .addParams("type",type)
                .addParams("cityId",cityId)
                .addParams("brandId",brandId)
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

    //版本更新
    public static void getUpdateVersionData(String url,HttpCallBack callBack){
        OkHttpUtils
                .post()
                .url(url)
                .build()
                .execute(callBack);
    }
}
