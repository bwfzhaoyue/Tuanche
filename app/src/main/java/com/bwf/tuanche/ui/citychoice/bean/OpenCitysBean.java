package com.bwf.tuanche.ui.citychoice.bean;

/**
 * Created by che on 2016/8/17
 * Description:.
 */
public class OpenCitysBean {

//    "openCitys": [
//    {
//        "id": 197,
//            "name": "鞍山",
//            "province": "辽宁",
//            "pinyin": "anshan",
//            "citycode": null,
//            "pname": "东北区",
//            "py": "as",
//            "openStatus": 0,
//            "manNum": null
//    },

    public String id;//197
    public String name;//鞍山
    public String province;//辽宁
    public String pinyin;//anshan
    public String citycode;//null
    public String pname;//东北区
    public String py;//as
    public String openStatus;//0
    public String manNum;//null

    @Override
    public String toString() {
        return "OpenCitysBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", citycode='" + citycode + '\'' +
                ", pname='" + pname + '\'' +
                ", py='" + py + '\'' +
                ", openStatus='" + openStatus + '\'' +
                ", manNum='" + manNum + '\'' +
                '}';
    }
}
