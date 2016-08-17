package com.bwf.tuanche.ui.mainpager.fragment;


import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.tuanche.R;

public class PromoteAndTabFragment extends BaseFragment {

    private ViewPager vp_promote;

    private LinearLayout ll_lpbuycar, ll_carsticker, ll_newmodle, ll_compareBX;

    private ImageView img_lpbuycar, img_carsticker, img_newmodle, img_compareBX;

    private TextView tv_lpbuycar, tv_carsticker, tv_newmodle, tv_compareBX;

    @Override
    protected int getResource() {
        return R.layout.fragment_promote_and_tab;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        vp_promote=findViewByIdNoCast(R.id.vp_promote);

        ll_lpbuycar=findViewByIdNoCast(R.id.ll_lpbuycar);
        ll_carsticker=findViewByIdNoCast(R.id.ll_carsticker);
        ll_newmodle=findViewByIdNoCast(R.id.ll_newmodle);
        ll_compareBX=findViewByIdNoCast(R.id.ll_compareBX);

        img_lpbuycar=findViewByIdNoCast(R.id.img_lpbuycar);
        img_carsticker=findViewByIdNoCast(R.id.img_carsticker);
        img_newmodle=findViewByIdNoCast(R.id.img_newmodle);
        img_compareBX=findViewByIdNoCast(R.id.img_compareBX);

        tv_lpbuycar=findViewByIdNoCast(R.id.tv_lpbuycar);
        tv_carsticker=findViewByIdNoCast(R.id.tv_carsticker);
        tv_newmodle=findViewByIdNoCast(R.id.tv_newmodle);
        tv_compareBX=findViewByIdNoCast(R.id.tv_compareBX);

    }

    @Override
    protected void initData() {
        setOnClick(ll_lpbuycar,ll_carsticker,ll_newmodle,ll_compareBX);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_lpbuycar:
                break;
            case R.id.ll_carsticker:
                break;
            case R.id.ll_newmodle:
                break;
            case R.id.ll_compareBX:
                break;
        }
    }
}
