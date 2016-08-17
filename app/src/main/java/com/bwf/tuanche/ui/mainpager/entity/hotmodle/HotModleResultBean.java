package com.bwf.tuanche.ui.mainpager.entity.hotmodle;

import com.bwf.framwork.base.BaseBean;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/17.
 * Description:Tuanche
 */
public class HotModleResultBean extends BaseBean {

    public List<HotModleResult> result;

    @Override
    public String toString() {
        return "HotModleResultBean{" +
                "result=" + result +
                '}';
    }
}
