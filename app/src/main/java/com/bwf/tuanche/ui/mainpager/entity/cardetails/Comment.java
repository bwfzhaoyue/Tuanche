package com.bwf.tuanche.ui.mainpager.entity.cardetails;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/18.
 * Description:Tuanche
 */
public class Comment {
    public String count;

    public double commentTotal;

    public List<CommentList> commentList ;

    @Override
    public String toString() {
        return "Comment{" +
                "commentList=" + commentList +
                ", count='" + count + '\'' +
                ", commentTotal=" + commentTotal +
                '}';
    }
}
