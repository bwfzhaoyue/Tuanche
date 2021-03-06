package com.bwf.tuanche.ui.mainpager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.tuanche.R;
import com.bwf.tuanche.application.MyApplication;
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
import com.bwf.tuanche.ui.search.SearchActivity;
import com.bwf.tuanche.ui.update.MyUpdatePopwindow;
import com.bwf.tuanche.view.LoadingView;
import com.bwf.tuanche.view.refresh.PullToRefreshLayout;
import com.bwf.tuanche.view.refresh.PullableScrollView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainPagerActivity extends BaseActivity implements Handler.Callback {

    private PromoteAndTabFragment frag_promote_tabs;
    private HotBrandFragment frag_hot_brand;
    private BannerFragment frag_banner;
    private HotModleFragment frag_hot_modle;
    private LinearLayout ll_search;

    private ImageView img_labe;
    private String cityId = "156";
    private PullableScrollView scroView_main;
    private PullToRefreshLayout scr_mainpager;
    private TextView tv_location;
    private int count = 10;
    private int offset = 0;
    private int isBuy = 2;
    private int X;
    private LoadingView main_boy;
    public boolean refresh;
    public boolean runBoy = true;
    private PullToRefreshLayout pullToRefreshLayout_This;
    private RelativeLayout rel_person, rel_mainpager;

    private TextView tv_my, tv_main, tv_order, tv_service, tv_search;

    private ImageView img_my, img_main, img_order, img_service, img_erweima;

    private boolean isUpdata;
    private LinearLayout ll_main, ll_order, ll_service, ll_my;
    private Handler handler;

    public int getContentViewId() {
        return R.layout.activity_main_pager;
    }

    @Override
    public void beforeInitView() {

        handler = new Handler(this);
        isUpdata = MyApplication.getMyApplication().isUpdata();
        if (isUpdata)
            handler.sendEmptyMessageDelayed(1, 1000);


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
        ll_search = findViewByIdNoCast(R.id.ll_search);
        img_labe = findViewByIdNoCast(R.id.img_labe);
        tv_location = findViewByIdNoCast(R.id.tv_location);
        scroView_main = findViewByIdNoCast(R.id.scroView_main);
        scr_mainpager = findViewByIdNoCast(R.id.scr_mainpager);
        main_boy = findViewByIdNoCast(R.id.main_boy);
        rel_mainpager = findViewByIdNoCast(R.id.rel_mainpager);
        rel_person = findViewByIdNoCast(R.id.rel_person);
        ll_main = findViewByIdNoCast(R.id.ll_main);
        ll_order = findViewByIdNoCast(R.id.ll_order);
        ll_service = findViewByIdNoCast(R.id.ll_service);
        ll_my = findViewByIdNoCast(R.id.ll_my);

        tv_my = findViewByIdNoCast(R.id.tv_my);
        tv_main = findViewByIdNoCast(R.id.tv_main);
        tv_order = findViewByIdNoCast(R.id.tv_order);
        tv_service = findViewByIdNoCast(R.id.tv_service);

        img_my = findViewByIdNoCast(R.id.img_my);
        img_main = findViewByIdNoCast(R.id.img_main);
        img_order = findViewByIdNoCast(R.id.img_order);
        img_service = findViewByIdNoCast(R.id.img_service);

        img_erweima = findViewByIdNoCast(R.id.img_erweima);
        tv_search = findViewByIdNoCast(R.id.tv_search);
    }

    @Override
    public void initData() {
        frag_promote_tabs.setCityId(cityId);
        frag_hot_brand.setCityId(cityId);
        scroView_main.setCanLoadMore(false);
        setOnClick(tv_search, img_labe, tv_location, ll_main, ll_order, ll_service, ll_my, img_erweima);
        scr_mainpager.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout_This = pullToRefreshLayout;
                getTopBrandData();
                getBrand();
                getBanner();
                getHotModleData();
                refresh = true;
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });
    }

    public void loadSucess(int i) {
        X += i;
        if (refresh) {
            if (X > 0) {
                pullToRefreshLayout_This.refreshFinish(PullToRefreshLayout.SUCCEED);
            } else
                pullToRefreshLayout_This.refreshFinish(PullToRefreshLayout.FAIL);
            refresh = false;
        }

        if (runBoy) {
            if (X > 0)
                main_boy.setLoadingFinish();
            else
                main_boy.setLoadFail();
            runBoy = false;
        }
        X = 0;

    }

    private int j;
    private int l;

    public void getI(int i) {
        j++;
        l += i;
        if (j == 4) {
            loadSucess(l);
            j = 0;
            l = 0;
        }
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {

            case R.id.tv_search:
                bundle.putString("cityId", cityId);
                IntentUtils.openActivity(this, SearchActivity.class, bundle);
                break;
            case R.id.img_labe:
                bundle.putString("cityId", cityId);
                IntentUtils.openActivity(this, ChooseCarActivity.class, bundle);
                break;
            case R.id.img_erweima:
                startActivityForResult(new Intent(this, CaptureActivity.class), 0);
                break;
            case R.id.tv_location:
                IntentUtils.openActivity(this, CityChoiceActivity.class);
                break;
            case R.id.ll_main:
                img_my.setImageResource(R.mipmap.nav_icon_my_nor);
                tv_my.setTextColor(Color.GRAY);

                img_main.setImageResource(R.mipmap.nav_icon_home_sel);
                tv_main.setTextColor(Color.RED);

                img_service.setImageResource(R.mipmap.nav_icon_server_nor);
                tv_service.setTextColor(Color.GRAY);

                img_order.setImageResource(R.mipmap.nav_icon_order_nor);
                tv_order.setTextColor(Color.GRAY);

                rel_mainpager.setVisibility(View.VISIBLE);
                rel_person.setVisibility(View.GONE);
                break;
            case R.id.ll_order:
                img_my.setImageResource(R.mipmap.nav_icon_my_nor);
                tv_my.setTextColor(Color.GRAY);

                img_main.setImageResource(R.mipmap.nav_icon_home_nor);
                tv_main.setTextColor(Color.GRAY);

                img_service.setImageResource(R.mipmap.nav_icon_server_nor);
                tv_service.setTextColor(Color.GRAY);

                img_order.setImageResource(R.mipmap.nav_icon_order_sel);
                tv_order.setTextColor(Color.RED);
                break;
            case R.id.ll_service:
                img_my.setImageResource(R.mipmap.nav_icon_my_nor);
                tv_my.setTextColor(Color.GRAY);

                img_main.setImageResource(R.mipmap.nav_icon_home_nor);
                tv_main.setTextColor(Color.GRAY);

                img_service.setImageResource(R.mipmap.nav_icon_server_sel);
                tv_service.setTextColor(Color.RED);

                img_order.setImageResource(R.mipmap.nav_icon_order_nor);
                tv_order.setTextColor(Color.GRAY);
                break;
            case R.id.ll_my:
                img_my.setImageResource(R.mipmap.nav_icon_my_sel);
                tv_my.setTextColor(Color.RED);

                img_main.setImageResource(R.mipmap.nav_icon_home_nor);
                tv_main.setTextColor(Color.GRAY);

                img_service.setImageResource(R.mipmap.nav_icon_server_nor);
                tv_service.setTextColor(Color.GRAY);

                img_order.setImageResource(R.mipmap.nav_icon_order_nor);
                tv_order.setTextColor(Color.GRAY);

                rel_mainpager.setVisibility(View.GONE);
                rel_person.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 拿到扫描二维码的结果 result
     * 处理方式为吐司通知，可进行更改
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            String result = data.getStringExtra(CodeUtils.RESULT_STRING);
            ToastUtil.showToast(result);
        }
    }

    public void getTopBrandData() {
        HttpHelper.getTopBrand(cityId, new HttpCallBack<NcResultBean>() {


            @Override
            public void onSuccess(NcResultBean result) {
                if (result != null) {
                    frag_promote_tabs.setResult(result);
                    if (refresh) {
                        getI(1);
                    }
                    if (runBoy) {
                        getI(1);
                    }
                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
                if (refresh) {
                    getI(0);
                }
                if (runBoy) {
                    getI(0);
                }
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
                    if (refresh) {
                        getI(1);
                    }
                    if (runBoy) {
                        getI(1);
                    }
                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
                if (refresh) {
                    getI(0);
                }
                if (runBoy) {
                    getI(0);
                }
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
                if (result != null) {

                    frag_hot_brand.setResult(result);
                    if (refresh) {
                        getI(1);
                    }
                    if (runBoy) {
                        getI(1);
                    }
                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
                if (refresh) {
                    getI(0);
                }
                if (runBoy) {
                    getI(0);
                }
            }
        });
    }

    /**
     * 获得首页banner
     */
    public void getBanner() {
        HttpHelper.getMainBanner(cityId, new HttpCallBack<BannerResult>() {
            public void onSuccess(BannerResult result) {
                if (result != null) {
                    frag_banner.setResult(result);
                    frag_promote_tabs.setBannerResult(result);
                    if (refresh) {
                        getI(1);
                    }
                    if (runBoy) {
                        getI(1);
                    }
                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
                if (refresh) {
                    getI(0);
                }
                if (runBoy) {
                    getI(0);
                }
            }
        });
    }


    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                MyUpdatePopwindow myUpdatePopwindow = new MyUpdatePopwindow(this);
                myUpdatePopwindow.showPopWindow(ll_search);
                MyApplication.getMyApplication().setUpdata(false);
                break;
            case 2:
                isBack = true;
                break;
        }
        return false;
    }

    private static final int TIMES = 2000;

    private boolean isBack = true;

    /**
     * 监听返回键，两秒内连续两次点击返回键退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {//按下返回键

            if (isBack) {
                ToastUtil.showToast("再点一次退出");
                isBack = false;
                handler.sendEmptyMessageDelayed(2, 2000);
            } else {
                //退出app
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
