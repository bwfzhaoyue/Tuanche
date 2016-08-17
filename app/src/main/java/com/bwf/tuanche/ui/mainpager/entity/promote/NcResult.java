package com.bwf.tuanche.ui.mainpager.entity.promote;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/17.
 * Description:Tuanche
 */
public class NcResult {
    public List<Oc> oc ;

    public List<Nc> nc ;

    public List<Tc> tc ;

    @Override
    public String toString() {
        return "NcResult{" +
                "nc=" + nc +
                ", oc=" + oc +
                ", tc=" + tc +
                '}';
    }
}
