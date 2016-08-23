package com.bwf.tuanche.ui.mainpager.entity.cardetails;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/18.
 * Description:Tuanche
 */
public class CommentList {

    public String userName;

    public String commentDate;

    public float score;

    public String content;

    public String memberPic;

    public List<CommentPicList> commentPicList ;

    public boolean fine;

    public boolean isDown = true;

    public int lineCount;

    public boolean ok=true;

    @Override
    public String toString() {
        return "CommentList{" +
                "commentDate='" + commentDate + '\'' +
                ", userName='" + userName + '\'' +
                ", score='" + score + '\'' +
                ", content='" + content + '\'' +
                ", memberPic='" + memberPic + '\'' +
                ", commentPicList=" + commentPicList +
                ", fine=" + fine +
                '}';
    }
}
