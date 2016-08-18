package com.bwf.tuanche.ui.mainpager.entity;

/**
 * Created by zengqiang on 2016/8/17.
 * Description:Tuanche
 */
public class Position_banner {
    public String subTitle;

    public String bigTitle;

    public String adpInfoUrl;

    public String positionId;

    public String iconUrl;

    public String positionType;

    public String adType;


    @Override
    public String toString() {
        return "Position_banner{" +
                "adpInfoUrl='" + adpInfoUrl + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", bigTitle='" + bigTitle + '\'' +
                ", positionId=" + positionId +
                ", iconUrl='" + iconUrl + '\'' +
                ", positionType=" + positionType +
                ", adType='" + adType + '\'' +
                '}';
    }
}
