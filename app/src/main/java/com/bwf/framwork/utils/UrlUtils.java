package com.bwf.framwork.utils;

/**
 * Created by Lizhangfeng on 2016/7/19 0019.
 * Description:
 */
public class UrlUtils {

    public static final String BASE_URL = "http://123.56.145.151:8080/TuanCheNetWork";
    //城市列表
    public static final String CITY_DATA=BASE_URL+"/bwf_TuanChe_Getcitys";
    //定位城市
    public static final String LOCATION_LOLATUDE=BASE_URL+"/bwf_TuanChe_QueryCityByLatitude";
    //版本更新
    public static final String VERSION_UPDATE=BASE_URL+"/bwf_TuanChe_VersionUpadteServlet";


    //品牌选车中的热门品牌

    //选车列表

    //条件选车
    public static final String TOPBRAND=BASE_URL+"/bwf_TuanChe_HomeServlet";

    //品牌选车中的热门品牌-赵玥
    public static final String HOT_CAR_TYPE = BASE_URL + "/bwf_TuanChe_TopBrand";

    //选车列表-赵玥
    public static final String CAR_TYPE_LIST = BASE_URL +"/bwf_TuanChe_XuanbrandmapServlet";

    //条件选车-赵玥
    public static final String CHOOSE_CAR_BY_CONDITION = BASE_URL + "/bwf_TuanChe_SelectCarInfosServlet";

    //根据车品牌获取车列表
    public static final String CAR_LIST_BY_BRAND = BASE_URL + "/bwf_TuanChe_BrandCarStyleServlet";

    public static final String MAIN_BANNER_URL = BASE_URL + "/bwf_TuanChe_BannerServlet";

    public static final String HOT_BRAND_URL = BASE_URL + "/bwf_TuanChe_TopBrand";


    public static final String CAR_DETAILS_URL = BASE_URL + "/bwf_TuanChe_BuyInfoServlet";

    public static final String MARRIGE_CAR_URL = BASE_URL + "/bwf_TuanChe_AdplistServlet";

    public static final String All_EVALUTE_URL = BASE_URL + "/bwf_TuanChe_BuyInfoEvaluateServlet";
    public static final String HOT_MODLE_URL=BASE_URL+"/bwf_TuanChe_Hotstyle";

    //热门搜索
    public static final String HOT_SEARCH=BASE_URL+"/bwf_TuanChe_SearchhotServlet";


}
