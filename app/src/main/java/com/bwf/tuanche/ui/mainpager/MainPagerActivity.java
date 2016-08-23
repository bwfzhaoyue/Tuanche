package com.bwf.tuanche.ui.mainpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.BannerResult;
import com.bwf.tuanche.ui.mainpager.entity.hotbrand.HotBrandResultBean;
import com.bwf.tuanche.ui.mainpager.entity.hotmodle.HotModleResultBean;
import com.bwf.tuanche.ui.mainpager.entity.promote.NcResultBean;
import com.bwf.tuanche.ui.mainpager.fragment.BannerFragment;
import com.bwf.tuanche.ui.mainpager.fragment.HotBrandFragment;
import com.bwf.tuanche.ui.mainpager.fragment.HotModleFragment;
import com.bwf.tuanche.ui.mainpager.fragment.PromoteAndTabFragment;
import com.bwf.tuanche.ui.update.MyUpdatePopwindow;

public class MainPagerActivity extends BaseActivity implements Handler.Callback{

    private PromoteAndTabFragment frag_promote_tabs;
    private HotBrandFragment frag_hot_brand;
    private BannerFragment frag_banner;
    private HotModleFragment frag_hot_modle;
    private LinearLayout ll_search;

    private int cityId = 156;
    private int count = 10;
    private int offset = 0;
    private int isBuy = 2;
    private Handler handler;

    public int getContentViewId() {
        return R.layout.activity_main_pager;
    }

    @Override
    public void beforeInitView() {


        handler = new Handler(this);
        handler.sendEmptyMessageDelayed(1,1000);



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
        ll_search = findViewByIdNoCast(R.id.ll_search);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    public void getTopBrandData() {
        HttpHelper.getTopBrand("" + cityId, new HttpCallBack<NcResultBean>() {


            @Override
            public void onSuccess(NcResultBean result) {
                if (result != null)
                    frag_promote_tabs.setResult(result);
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }

    /**
     * 获得热门车型
     */

    public void getHotModleData() {
        HttpHelper.getHotModle("156", "2", "0", new HttpCallBack<HotModleResultBean>() {

            @Override
            public void onSuccess(HotModleResultBean result) {
                if (result != null)
                    frag_hot_modle.setResult(result);
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
            }
        });
    }

    /**
     * 获得首页热门品牌(brand)
     */

    public void getBrand() {
        HttpHelper.getHotBrand("156", "2", new HttpCallBack<HotBrandResultBean>() {
            @Override
            public void onSuccess(HotBrandResultBean result) {
                if (result != null)
                    frag_hot_brand.setResult(result);
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
            }
        });
    }

    /**
     * 获得首页banner
     */
    public void getBanner() {
        HttpHelper.getMainBanner("" + cityId, new HttpCallBack<BannerResult>() {


            @Override
            public void onSuccess(BannerResult result) {
                if (result != null) {
                    frag_banner.setResult(result);
                    frag_promote_tabs.setBannerResult(result);
                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
            }
        });
    }


    @Override
    public boolean handleMessage(Message message) {
        switch (message.what){
            case 1:
                MyUpdatePopwindow myUpdatePopwindow = new MyUpdatePopwindow(this);
                myUpdatePopwindow.showPopWindow(ll_search);
                break;
        }
        return false;
    }
}
