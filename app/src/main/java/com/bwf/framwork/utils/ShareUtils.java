package com.bwf.framwork.utils;

import android.app.Activity;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * Created by zengqiang on 2016/8/10.
 * Description:ThirdShare
 */
public class ShareUtils {

    public static void ThirdShare(Activity activity, SHARE_MEDIA share_media, String title, String desc
    , String url, String imageUrl, UMShareListener umShareListener){
        new ShareAction(activity).setPlatform(share_media)
                .withTitle(title).withText(desc).withTargetUrl(url)
                .withMedia(new UMImage(activity,imageUrl)).setListenerList(umShareListener).share();
    }
}
