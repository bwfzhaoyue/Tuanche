package com.bwf.tuanche.ui.mainpager.entity.promote;

/**
 * Created by zengqiang on 2016/8/17.
 * Description:Tuanche
 */
public class Nc {
    public String weight;

    public String name;

    public String pic;

    public boolean show;

    public String type;

    public String modules;

    public String is_ng;

    public String is_login;

    public String url;

    @Override
    public String toString() {
        return "Nc{" +
                "is_login='" + is_login + '\'' +
                ", weight='" + weight + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", show=" + show +
                ", type='" + type + '\'' +
                ", modules='" + modules + '\'' +
                ", is_ng='" + is_ng + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
