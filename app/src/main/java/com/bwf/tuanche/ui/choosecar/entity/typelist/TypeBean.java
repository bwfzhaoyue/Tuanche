package com.bwf.tuanche.ui.choosecar.entity.typelist;

/**
 * author zhaoyue
 * Description
 */
public class TypeBean implements Comparable<TypeBean>{

    public String id;

    public String pinyin;

    public String name;

    public String logo;

    public String baseNum;

    public String penname;

    @Override
    public String toString() {
        return "TypeBean{" +
                "id='" + id + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", baseNum='" + baseNum + '\'' +
                ", penname='" + penname + '\'' +
                '}';
    }

    @Override
    public int compareTo(TypeBean typeBean) {
        return this.pinyin.compareTo(typeBean.pinyin);
    }
}
