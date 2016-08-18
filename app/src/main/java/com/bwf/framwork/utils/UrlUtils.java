package com.bwf.framwork.utils;

/**
 * Created by Lizhangfeng on 2016/7/19 0019.
 * Description:
 */
public class UrlUtils {

    public static final String BASE_URL = "http://123.56.145.151:8080/TuanCheNetWork";
    //城市
    public static final String CITY_DATA=BASE_URL+"/bwf_TuanChe_Getcitys";
    //定位
    public static final String LOCATION_LOLATUDE=BASE_URL+"/bwf_TuanChe_QueryCityByLatitude";

    public static final String TOPBRAND=BASE_URL+"/bwf_TuanChe_HomeServlet";

<<<<<<< HEAD
    //品牌选车中的热门品牌-赵玥
    public static final String HOT_CAR_TYPE = BASE_URL + "/bwf_TuanChe_TopBrand";

    //选车列表-赵玥
    public static final String CAR_TYPE_LIST = BASE_URL +"/bwf_TuanChe_XuanbrandmapServlet";

    //条件选车-赵玥
    public static final String CHOOSE_CAR_BY_CONDITION = BASE_URL + "/bwf_TuanChe_SelectCarInfosServlet";

    //根据车品牌获取车列表
    public static final String CAR_LIST_BY_BRAND = BASE_URL + "/bwf_TuanChe_BrandCarStyleServlet";
=======
    //品牌选车中的热门品牌
    public static final String HOT_CAR_TYPE = BASE_URL + "/bwf_TuanChe_TopBrand";

    //选车列表
    public static final String CAR_TYPE_LIST = BASE_URL +"/bwf_TuanChe_SelectTopBrand";

    //条件选车
    public static final String CHOOSE_CAR_BY_CONDITION = BASE_URL + "/bwf_TuanChe_SelectCarInfosServlet";
>>>>>>> e94555ec5e6524225d7567a4ea87a5320c91733e

    public static final String MAIN_BANNER_URL=BASE_URL+"/bwf_TuanChe_BannerServlet";

    public static final String HOT_BRAND_URL=BASE_URL+"/bwf_TuanChe_TopBrand";

    public static final String HOT_MODLE_URL=BASE_URL+"/bwf_TuanChe_Hotstyle";


}
