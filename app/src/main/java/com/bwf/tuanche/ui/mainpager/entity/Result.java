package com.bwf.tuanche.ui.mainpager.entity;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/17.
 * Description:Tuanche
 */
public class Result {

    public List<Header_banner> header_banner ;

    public List<Center_banner> center_banner ;

    public List<Position_banner> position_banner ;


    @Override
    public String toString() {
        return "Result{" +
                "center_banner=" + center_banner +
                ", header_banner=" + header_banner +
                ", position_banner=" + position_banner +
                '}';
    }
}
