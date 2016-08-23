package com.bwf.tuanche.ui.update.bean;

/**
 * Created by che on 2016/8/22
 * Description:.
 */
public class UpdateBean {
//    "versionCode": 20160729,
//            "versionName": "2.4.3",
//            "url": "http://download.tuanche.com/app/android/tuancheapp.apk",
//            "md5file": "faedcabaad6c68d6597e93404915172b",
//            "description": "1、优化首页，优质好车抢先看 \n\n2、优化搜索，更精准、更快捷 \n\n3、优化详情页，报名更方便 \n\n4、优化订单，提升用户的体验",
//            "upgradeIntervalWarn": 100,
//            "isFourceUpGrade": false,
//            "isPromtUpGrade": true,
//            "titleText": "发现新版本",
//            "giveUpText": "以后再说",
//            "upgradeText": "立即更新",
//            "textPic": "http://pic.tuanche.com/icon/base/common/version.png",
//            "isForce": null,
//            "specificEdition": null,
//            "minVersion": null

    public String versionCode;
    public String versionName;//"2.4.3"
    public String url;
    public String md5file;
    public String description;//"1、优化首页，优质好车抢先看 \n\n2、优化搜索，更精准、更快捷
    public String upgradeIntervalWarn;
    public String isFourceUpGrade;
    public String isPromtUpGrade;
    public String titleText;
    public String giveUpText;
    public String upgradeText;
    public String textPic;
    public String isForce;
    public String specificEdition;
    public String minVersion;

    @Override
    public String toString() {
        return "UpdateBean{" +
                "versionCode='" + versionCode + '\'' +
                ", versionName='" + versionName + '\'' +
                ", url='" + url + '\'' +
                ", md5file='" + md5file + '\'' +
                ", description='" + description + '\'' +
                ", upgradeIntervalWarn='" + upgradeIntervalWarn + '\'' +
                ", isFourceUpGrade='" + isFourceUpGrade + '\'' +
                ", isPromtUpGrade='" + isPromtUpGrade + '\'' +
                ", titleText='" + titleText + '\'' +
                ", giveUpText='" + giveUpText + '\'' +
                ", upgradeText='" + upgradeText + '\'' +
                ", textPic='" + textPic + '\'' +
                ", isForce='" + isForce + '\'' +
                ", specificEdition='" + specificEdition + '\'' +
                ", minVersion='" + minVersion + '\'' +
                '}';
    }
}
