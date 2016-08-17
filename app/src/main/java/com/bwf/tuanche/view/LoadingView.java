package com.bwf.tuanche.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bwf.framwork.utils.LogUtils;
import com.bwf.tuanche.R;

/**
 * author zhaoyue
 * Description:加载中的动画显示View
 */
public class LoadingView extends RelativeLayout {

    private ImageView img_loading;

    private LinearLayout ll_loading,ll_loadingerr;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

        View contentView = inflate(context, R.layout.view_loading,null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(layoutParams);
        addView(contentView);
        setLayoutParams(layoutParams);
        img_loading = (ImageView) findViewById(R.id.img_loading);
        ll_loading = (LinearLayout) findViewById(R.id.ll_loading);
        ll_loadingerr = (LinearLayout) findViewById(R.id.ll_loadingerr);
        AnimationDrawable anim = (AnimationDrawable)(img_loading.getBackground());
        anim.start();
    }

    /**
     * 设置成加载中
     */
    public void setOnLoad(){
        setVisibility(VISIBLE);
        ll_loading.setVisibility(VISIBLE);
        ll_loadingerr.setVisibility(GONE);
        AnimationDrawable anim = (AnimationDrawable)(img_loading.getBackground());
        anim.start();
        LogUtils.e("LoadingView状态：onLoad");
    }
    /**
     * 设置成加载失败
     */
    public void setLoadFail(){
        setVisibility(VISIBLE);
        ll_loading.setVisibility(GONE);
        ll_loadingerr.setVisibility(VISIBLE);
        LogUtils.e("LoadingView状态：LoadFail");
    }

    /**
     * 设置成加载完成（全隐藏）
     */
    public void setLoadingFinish(){
        setVisibility(GONE);
        LogUtils.e("LoadingView状态：LoadingFinish");
    }
}
