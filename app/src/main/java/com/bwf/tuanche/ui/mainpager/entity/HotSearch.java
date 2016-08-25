package com.bwf.tuanche.ui.mainpager.entity;

import com.bwf.framwork.base.BaseBean;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/24.
 * Description:Tuanche
 */
public class HotSearch extends BaseBean {
    public List<String> result;

    @Override
    public String toString() {
        return "HotSearch{" +
                "result=" + result +
                '}';
    }
}
