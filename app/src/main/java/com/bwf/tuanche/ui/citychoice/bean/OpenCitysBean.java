package com.bwf.tuanche.ui.citychoice.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by che on 2016/8/17
 * Description:.
 */
public class OpenCitysBean implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.province);
        dest.writeString(this.pinyin);
        dest.writeString(this.citycode);
        dest.writeString(this.pname);
        dest.writeString(this.py);
        dest.writeString(this.openStatus);
        dest.writeString(this.manNum);
    }

    public OpenCitysBean() {
    }

    protected OpenCitysBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.province = in.readString();
        this.pinyin = in.readString();
        this.citycode = in.readString();
        this.pname = in.readString();
        this.py = in.readString();
        this.openStatus = in.readString();
        this.manNum = in.readString();
    }

    public static final Parcelable.Creator<OpenCitysBean> CREATOR = new Parcelable.Creator<OpenCitysBean>() {
        @Override
        public OpenCitysBean createFromParcel(Parcel source) {
            return new OpenCitysBean(source);
        }

        @Override
        public OpenCitysBean[] newArray(int size) {
            return new OpenCitysBean[size];
        }
    };
}
