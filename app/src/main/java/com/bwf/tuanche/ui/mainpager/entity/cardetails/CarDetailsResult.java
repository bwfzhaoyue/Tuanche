package com.bwf.tuanche.ui.mainpager.entity.cardetails;

import java.io.Serializable;

/**
 * Created by zengqiang on 2016/8/18.
 * Description:Tuanche
 */
public class CarDetailsResult implements Serializable{
    public double commentTotal;

    public String regular4sShop;

    public String isFree;

    public String isLowest;

    public String isPriorityBy4S;

    public String isAllFree;

    public Comment comment;

    public String groupbuyDate;

    public String groupbuyWeek;

    public String salerId;

    public String id;

    public String logo;

    public String styleName;

    public String factoryPrice;

    public String content;

    public String isBuy;

    public String manNum;

    public String state;

    public String isCompensate;

    public String addUp;

    public String brandLogo;

    public String flowUrl;

    public String questionUrl;

    public BuyWays buyWays;

    public String manNumDesc;

    public String brandName;

    public String brandPic;

    public SsgTz ssgTz;

    public String passNum;

    public String brandGroupStyleNum;

    public String brandGroupStyleManNum;

    public String shareBrandTitle;

    public String shareStyleTitle;

    public String shareDesc;

    public String shareBrandUrl;

    public String shareStyleUrl;

    public String tcbzDesc;

    public String brandId;

    public String styleId;

    public String saveUpString;

    public String saveUpMoney;

    public boolean special;

    @Override
    public String toString() {
        return "CarDetailsResult{" +
                "addUp='" + addUp + '\'' +
                ", commentTotal=" + commentTotal +
                ", regular4sShop='" + regular4sShop + '\'' +
                ", isFree='" + isFree + '\'' +
                ", isLowest='" + isLowest + '\'' +
                ", isPriorityBy4S='" + isPriorityBy4S + '\'' +
                ", isAllFree='" + isAllFree + '\'' +
                ", comment=" + comment +
                ", groupbuyDate='" + groupbuyDate + '\'' +
                ", groupbuyWeek='" + groupbuyWeek + '\'' +
                ", salerId='" + salerId + '\'' +
                ", id='" + id + '\'' +
                ", logo='" + logo + '\'' +
                ", styleName='" + styleName + '\'' +
                ", factoryPrice='" + factoryPrice + '\'' +
                ", content='" + content + '\'' +
                ", isBuy='" + isBuy + '\'' +
                ", manNum='" + manNum + '\'' +
                ", state='" + state + '\'' +
                ", isCompensate='" + isCompensate + '\'' +
                ", brandLogo='" + brandLogo + '\'' +
                ", flowUrl='" + flowUrl + '\'' +
                ", questionUrl='" + questionUrl + '\'' +
                ", buyWays=" + buyWays +
                ", manNumDesc='" + manNumDesc + '\'' +
                ", brandName='" + brandName + '\'' +
                ", brandPic='" + brandPic + '\'' +
                ", ssgTz=" + ssgTz +
                ", passNum='" + passNum + '\'' +
                ", brandGroupStyleNum='" + brandGroupStyleNum + '\'' +
                ", brandGroupStyleManNum='" + brandGroupStyleManNum + '\'' +
                ", shareBrandTitle='" + shareBrandTitle + '\'' +
                ", shareStyleTitle='" + shareStyleTitle + '\'' +
                ", shareDesc='" + shareDesc + '\'' +
                ", shareBrandUrl='" + shareBrandUrl + '\'' +
                ", shareStyleUrl='" + shareStyleUrl + '\'' +
                ", tcbzDesc='" + tcbzDesc + '\'' +
                ", brandId='" + brandId + '\'' +
                ", styleId='" + styleId + '\'' +
                ", saveUpString='" + saveUpString + '\'' +
                ", saveUpMoney='" + saveUpMoney + '\'' +
                ", special=" + special +
                '}';
    }


}
