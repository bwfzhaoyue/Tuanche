package com.bwf.tuanche.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bwf.framwork.utils.DisplayUtil;
import com.bwf.tuanche.R;

/**
 * Created by zengqiang on 2016/8/25.
 * Description:Tuanche
 */
public class SanJiaoPopWindow extends PopupWindow {

    private TextView tv_pop;


    public SanJiaoPopWindow(Context context) {
        this(context, null);
    }

    public SanJiaoPopWindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SanJiaoPopWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.san_jiao_pop, null);
        this.setContentView(view);
        this.setHeight(DisplayUtil.getDensity_Height(context) / 10);
        this.setWidth(DisplayUtil.getDensity_Width(context) * 3 / 5);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        tv_pop = (TextView) view.findViewById(R.id.tv_pop);
        tv_pop.setText("  1、团长还在帮你砍价，还不确定底价"+"\n"+"  2、团购价格比较低，厂家不允许公开，公"+"\n"+"开后团购可能取消，从而影响团友的利益");
    }

    /**
     * 显示Popwindow
     */

    public void showPopWindow(View view){
        if (!isShowing()){
            this.showAsDropDown(view);//显示在view的下方
        }


    }


}
