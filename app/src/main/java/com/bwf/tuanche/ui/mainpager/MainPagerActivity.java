package com.bwf.tuanche.ui.mainpager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.ChooseCarActivity;
import com.bwf.tuanche.ui.citychoice.CityChoiceActivity;
import com.bwf.tuanche.ui.mainpager.entity.BannerResult;
import com.bwf.tuanche.ui.mainpager.entity.hotbrand.HotBrandResultBean;
import com.bwf.tuanche.ui.mainpager.entity.hotmodle.HotModleResultBean;
import com.bwf.tuanche.ui.mainpager.entity.promote.NcResultBean;
import com.bwf.tuanche.ui.mainpager.fragment.BannerFragment;
import com.bwf.tuanche.ui.mainpager.fragment.HotBrandFragment;
import com.bwf.tuanche.ui.mainpager.fragment.HotModleFragment;
import com.bwf.tuanche.ui.mainpager.fragment.PromoteAndTabFragment;
import com.bwf.tuanche.view.refresh.PullToRefreshLayout;
import com.bwf.tuanche.view.refresh.PullableScrollView;

public class MainPagerActivity extends BaseActivity {

    private PromoteAndTabFragment frag_promote_tabs;
    private HotBrandFragment frag_hot_brand;
    private BannerFragment frag_banner;
    private HotModleFragment frag_hot_modle;
    private ImageView img_labe;
    private String cityId = "156";
    private PullableScrollView scroView_main;
    private PullToRefreshLayout scr_mainpager;
    private TextView tv_location;
    private int count = 10;
    private int offset = 0;
    private int isBuy = 2;
    private boolean brand, topBrand, banner, modle;

    public int getContentViewId() {
        return R.layout.activity_main_pager;
    }

    @Override
    public void beforeInitView() {
//        cityId=getIntent().getStringExtra("cityId");
        getTopBrandData();
        getBrand();
        getBanner();
        getHotModleData();
    }

    @Override
    public void initView() {
        frag_promote_tabs = (PromoteAndTabFragment) getSupportFragmentManager().findFragmentById(R.id.frag_promote_tabs);
        frag_hot_brand = (HotBrandFragment) getSupportFragmentManager().findFragmentById(R.id.frag_hot_brand);
        frag_banner = (BannerFragment) getSupportFragmentManager().findFragmentById(R.id.frag_banner);
        frag_hot_modle = (HotModleFragment) getSupportFragmentManager().findFragmentById(R.id.frag_hot_modle);
        img_labe = findViewByIdNoCast(R.id.img_labe);
        tv_location = findViewByIdNoCast(R.id.tv_location);
        scroView_main = findViewByIdNoCast(R.id.scroView_main);
        scr_mainpager = findViewByIdNoCast(R.id.scr_mainpager);
    }

    @Override
    public void initData() {
        frag_promote_tabs.setCityId(cityId);
        frag_hot_brand.setCityId(cityId);
        scroView_main.setCanLoadMore(false);
        setOnClick(img_labe, tv_location);
        scr_mainpager.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                getTopBrandData();
                getBrand();
                getBanner();
                getHotModleData();


                pullToRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!brand && !topBrand && !banner && !modle) {
                            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
                        } else
                            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }
                }, 2000);


            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.img_labe:
                bundle.putString("cityId", cityId);
                IntentUtils.openActivity(this, ChooseCarActivity.class, bundle);
                break;
            case R.id.tv_location:
                IntentUtils.openActivity(this, CityChoiceActivity.class);
                break;
        }
    }

    public void getTopBrandData() {
        HttpHelper.getTopBrand(cityId, new HttpCallBack<NcResultBean>() {


            @Override
            public void onSuccess(NcResultBean result) {
                if (result != null) {

                    frag_promote_tabs.setResult(result);
                    topBrand = true;
                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
                topBrand = false;
            }
        });
    }

    /**
     * 获得热门车型
     */

    public void getHotModleData() {
        HttpHelper.getHotModle(cityId, "20", "10", new HttpCallBack<HotModleResultBean>() {

            @Override
            public void onSuccess(HotModleResultBean result) {
                if (result != null) {
                    frag_hot_modle.setResult(result);
                    modle = true;
                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
                modle = false;
            }
        });
    }

    /**
     * 获得首页热门品牌(brand)
     */

    public void getBrand() {
        HttpHelper.getHotBrand(cityId, "2", new HttpCallBack<HotBrandResultBean>() {
            @Override
            public void onSuccess(HotBrandResultBean result) {
                if (result != null)
                    brand = true;
                frag_hot_brand.setResult(result);
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
                brand = false;
            }
        });
    }

    /**
     * 获得首页banner
     */
    public void getBanner() {
        HttpHelper.getMainBanner(cityId, new HttpCallBack<BannerResult>() {


            @Override
            public void onSuccess(BannerResult result) {
                if (result != null) {
                    frag_banner.setResult(result);
                    frag_promote_tabs.setBannerResult(result);
                    banner = true;
                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
                banner = false;
            }
        });
    }
}
