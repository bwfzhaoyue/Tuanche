package com.bwf.tuanche.ui.mainpager.entity.hotbrand;

/**
 * Created by zengqiang on 2016/8/17.
 * Description:Tuanche
 */
public class ListHotBrand {
    public String id;

    public String name;

    public String logo;

    public String baseNum;

    public String modelType;

    @Override
    public String toString() {
        return "ListHotBrand{" +
                "baseNum=" + baseNum +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", modelType=" + modelType +
                '}';
    }
}
