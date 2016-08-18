package com.bwf.tuanche.ui.citychoice.bean;

/**
 * Created by che on 2016/8/18
 * Description:.
 */
public class LocationCityBean {
//    "id": 156,
//            "name": "成都",
//            "pinyin": "chengdu",
//            "py": "cd",
//            "openStatus": 1

    private String id;//156
    private String name;//成都
    private String pinyin;//chengdu
    private String py;//cd
    private String openStatus;//1

    @Override
    public String toString() {
        return "LocationCityBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", py='" + py + '\'' +
                ", openStatus='" + openStatus + '\'' +
                '}';
    }
}
