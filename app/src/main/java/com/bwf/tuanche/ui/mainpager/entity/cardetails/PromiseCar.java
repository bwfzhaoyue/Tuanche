package com.bwf.tuanche.ui.mainpager.entity.cardetails;

/**
 * Created by zengqiang on 2016/8/18.
 * Description:Tuanche
 */
public class PromiseCar {


    public String id;
    public String dataType;
    public String datakey;
    public String dataValue;
    public String title;
    public String  describe ;
    public String linkurl;
    public String  imgurl;
    public String versionCode;
    public String sourceId;
    public String  sort;
    public String status;
    public String isDel;
    public String createTime;
    public String ts;

    @Override
    public String toString() {
        return "PromiseCar{" +
                "createTime='" + createTime + '\'' +
                ", id='" + id + '\'' +
                ", dataType='" + dataType + '\'' +
                ", datakey='" + datakey + '\'' +
                ", dataValue='" + dataValue + '\'' +
                ", title='" + title + '\'' +
                ", describe='" + describe + '\'' +
                ", linkurl='" + linkurl + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", sort='" + sort + '\'' +
                ", status='" + status + '\'' +
                ", isDel='" + isDel + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
