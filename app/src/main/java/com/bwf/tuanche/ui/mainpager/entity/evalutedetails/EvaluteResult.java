package com.bwf.tuanche.ui.mainpager.entity.evalutedetails;

import com.bwf.tuanche.ui.mainpager.entity.cardetails.CommentList;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/23.
 * Description:Tuanche
 */
public class EvaluteResult {
    public String count;

    public double commentTotal;

    public double priceScore;

    public double salerScore;

    public double shopScore;

    public String offset;

    public List<CommentList> commentList ;

    @Override
    public String toString() {
        return "EvaluteResult{" +
                "commentList=" + commentList +
                ", count='" + count + '\'' +
                ", commentTotal=" + commentTotal +
                ", priceScore=" + priceScore +
                ", salerScore=" + salerScore +
                ", shopScore=" + shopScore +
                ", offset='" + offset + '\'' +
                '}';
    }
}
