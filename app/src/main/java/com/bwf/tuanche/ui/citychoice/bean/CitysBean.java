package com.bwf.tuanche.ui.citychoice.bean;

import java.util.List;

/**
 * Created by che on 2016/8/18
 * Description:.
 */
public class CitysBean {

    public List<HotCitysBean> hotCitys;
    public List<OpenCitysBean> openCitys;

    @Override
    public String toString() {
        return "CitysBean{" +
                "hotCitys=" + hotCitys +
                ", openCitys=" + openCitys +
                '}';
    }
}
