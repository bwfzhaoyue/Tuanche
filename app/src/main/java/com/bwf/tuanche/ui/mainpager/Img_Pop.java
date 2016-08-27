package com.bwf.tuanche.ui.mainpager;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.fragment.adpter.Img_Pop_Adapter;

/**
 * Created by zengqiang on 2016/8/25.
 * Description:Tuanche
 */
public class Img_Pop extends BaseActivity {
    private Img_Pop_Adapter adapter;
    private ViewPager vp_pop;
    private int position;
    @Override
    public int getContentViewId() {
        return R.layout.img_pop;
    }

    @Override
    public void beforeInitView() {
        position=getIntent().getIntExtra("position",0);
    }

    @Override
    public void initView() {
        vp_pop=findViewByIdNoCast(R.id.vp_pop);
    }

    @Override
    public void initData() {
        adapter=new Img_Pop_Adapter(this);
        vp_pop.setAdapter(adapter);
        vp_pop.setCurrentItem(position);
    }

    @Override
    public void onClick(View view) {

    }
}
