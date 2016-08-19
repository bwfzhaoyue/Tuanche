package com.bwf.tuanche.ui.choosecar.entity.typelist;

import java.util.List;

/**
 * author zhaoyue
 * Description
 */
public class TypeBeanGroup implements Comparable<TypeBeanGroup>{

    public String penname;

    public List<TypeBean> typeBeanList;

    @Override
    public int compareTo(TypeBeanGroup typeBeanGroup) {
        return this.penname.compareTo(typeBeanGroup.penname);
    }
}
