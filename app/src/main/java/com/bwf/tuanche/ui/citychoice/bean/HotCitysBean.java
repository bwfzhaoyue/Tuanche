package com.bwf.tuanche.ui.citychoice.bean;

/**
 * Created by che on 2016/8/17
 * Description:.
 */
public class HotCitysBean {

//    "hotCitys": [
//    {
//        "id": 10,
//            "name": "北京",
//            "province": "直辖市",
//            "pinyin": "beijing",
//            "pname": "直辖市",
//            "py": "bj",
//            "openStatus": 1
//    },

    public String id;//10
    public String name;//北京
    public String province;//直辖市   四川
    public String pinyin;//beijing
    public String pname;//直辖市   西南区
    public String py;//bj
    public String openStatus;//1

    @Override
    public String toString() {
        return "HotCitysBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", pname='" + pname + '\'' +
                ", py='" + py + '\'' +
                ", openStatus='" + openStatus + '\'' +
                '}';
    }

}
