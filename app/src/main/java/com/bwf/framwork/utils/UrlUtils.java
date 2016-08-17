package com.bwf.framwork.utils;

/**
 * Created by Lizhangfeng on 2016/7/19 0019.
 * Description:
 */
public class UrlUtils {

    public static final String BASE_URL = "http://123.56.145.151:8080/TuanCheNetWork/";

    //品牌选车中的热门品牌
    public static final String HOT_CAR_TYPE = BASE_URL + "bwf_TuanChe_TopBrand";

    //选车列表
    public static final String CAR_TYPE_LIST = BASE_URL +"bwf_TuanChe_SelectTopBrand";

    //条件选车
    public static final String CHOOSE_CAR_BY_CONDITION = BASE_URL + "bwf_TuanChe_SelectCarInfosServlet";

}
