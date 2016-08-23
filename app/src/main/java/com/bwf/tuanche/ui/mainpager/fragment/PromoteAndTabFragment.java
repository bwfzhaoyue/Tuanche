package com.bwf.tuanche.ui.mainpager.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.CenterBannerDetailActivity;
import com.bwf.tuanche.ui.mainpager.entity.BannerResult;
import com.bwf.tuanche.ui.mainpager.entity.promote.NcResultBean;
import com.facebook.drawee.view.SimpleDraweeView;

public class PromoteAndTabFragment extends BaseFragment {

    private SimpleDraweeView vp_promote;

    private LinearLayout ll_lpbuycar, ll_carsticker, ll_newmodle, ll_compareBX;

    private ImageView img_lpbuycar, img_carsticker, img_newmodle, img_compareBX;

    private TextView tv_lpbuycar, tv_carsticker, tv_newmodle, tv_compareBX;

    private NcResultBean result;

    private BannerResult bannerResult;

    @Override
    protected int getResource() {
        return R.layout.fragment_promote_and_tab;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        vp_promote = findViewByIdNoCast(R.id.vp_promote);

        ll_lpbuycar = findViewByIdNoCast(R.id.ll_lpbuycar);
        ll_carsticker = findViewByIdNoCast(R.id.ll_carsticker);
        ll_newmodle = findViewByIdNoCast(R.id.ll_newmodle);
        ll_compareBX = findViewByIdNoCast(R.id.ll_compareBX);

        img_lpbuycar = findViewByIdNoCast(R.id.img_lpbuycar);
        img_carsticker = findViewByIdNoCast(R.id.img_carsticker);
        img_newmodle = findViewByIdNoCast(R.id.img_newmodle);
        img_compareBX = findViewByIdNoCast(R.id.img_compareBX);

        tv_lpbuycar = findViewByIdNoCast(R.id.tv_lpbuycar);
        tv_carsticker = findViewByIdNoCast(R.id.tv_carsticker);
        tv_newmodle = findViewByIdNoCast(R.id.tv_newmodle);
        tv_compareBX = findViewByIdNoCast(R.id.tv_compareBX);

    }

    @Override
    protected void initData() {
        setOnClick(ll_lpbuycar, ll_carsticker, ll_newmodle, ll_compareBX);
        if (result != null) {
            img_lpbuycar.setImageURI(Uri.parse(result.result.nc.get(0).pic));
            img_carsticker.setImageURI(Uri.parse(result.result.nc.get(1).pic));
            img_newmodle.setImageURI(Uri.parse(result.result.nc.get(2).pic));
            img_compareBX.setImageURI(Uri.parse(result.result.nc.get(3).pic));
            tv_lpbuycar.setText(result.result.nc.get(0).name);
            tv_carsticker.setText(result.result.nc.get(1).name);
            tv_newmodle.setText(result.result.nc.get(2).name);
            tv_compareBX.setText(result.result.nc.get(3).name);
        }
        if (bannerResult!=null){
            vp_promote.setImageURI(Uri.parse(bannerResult.result.header_banner.get(0).adImgUrl));
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();
        switch (view.getId()) {
            case R.id.ll_lpbuycar:
                break;
            case R.id.ll_carsticker:
                bundle.putString("shareUrl",result.result.nc.get(1).url);
                bundle.putString("adName",result.result.nc.get(1).name);
                IntentUtils.openActivity(getActivity(), CenterBannerDetailActivity.class,bundle);
                break;
            case R.id.ll_newmodle:
                break;
            case R.id.ll_compareBX:
                bundle.putString("shareUrl",result.result.nc.get(3).url);
                bundle.putString("adName",result.result.nc.get(3).name);
                IntentUtils.openActivity(getActivity(), CenterBannerDetailActivity.class,bundle);
                break;
        }
    }

    public void setResult(NcResultBean result) {
        this.result = result;
        initData();
    }

    public void setBannerResult(BannerResult bannerResult) {
        this.bannerResult = bannerResult;
        initData();
    }
}
