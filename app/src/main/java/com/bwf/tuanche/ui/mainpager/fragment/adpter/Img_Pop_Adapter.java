package com.bwf.tuanche.ui.mainpager.fragment.adpter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwf.framwork.image.ImageLoader;
import com.bwf.tuanche.application.MyApplication;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengqiang on 2016/8/25.
 * Description:Tuanche
 */
public class Img_Pop_Adapter extends PagerAdapter {

    private Activity activity;
    private List<String> comPicList;
    private List<SimpleDraweeView> imageViews;

    public Img_Pop_Adapter(Activity activity) {
        this.activity = activity;
        imageViews = new ArrayList<>();
        comPicList = MyApplication.getMyApplication().getPicLists();
        if (comPicList != null && !comPicList.isEmpty()) {
            for (String string : comPicList) {
                SimpleDraweeView imageView = new SimpleDraweeView(activity);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageViews.add(imageView);
            }
        }
    }

    @Override
    public int getCount() {
        return imageViews == null ? 0 : imageViews.size() * 100;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (imageViews.size() > 0)
            container.removeView(imageViews.get(position % imageViews.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleDraweeView imageView = null;
        if (imageViews.size() > 0) {
            imageView = imageViews.get(position % imageViews.size());
            ImageLoader.getInstance().disPlayImage(imageView, comPicList.get(position % comPicList.size()));
            if (imageView.getParent() == null) {
                container.addView(imageView);
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (activity != null && !activity.isFinishing()) {
                        activity.finish();
                    }
                }
            });
        }
        return imageView;
    }
}
