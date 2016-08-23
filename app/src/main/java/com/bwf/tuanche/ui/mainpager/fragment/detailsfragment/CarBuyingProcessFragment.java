package com.bwf.tuanche.ui.mainpager.fragment.detailsfragment;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.tuanche.R;

public class CarBuyingProcessFragment extends BaseFragment {

    private WebView sdv_buy_process;
    @Override
    protected int getResource() {
        return R.layout.fragment_car_buying_process;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        sdv_buy_process=findViewByIdNoCast(R.id.sdv_buy_process);
    }

    @Override
    protected void initData() {
//        ImageLoader.getInstance().disPlayImage(sdv_process,"http://123.56.145.151:8080/TuanCheNetWork/bwf_TuanChe_BuyInfoNogroupServlet");
        sdv_buy_process.loadUrl("http://123.56.145.151:8080/TuanCheNetWork/bwf_TuanChe_BuyInfoNogroupServlet");
        //设置滚动条样式
        sdv_buy_process.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        sdv_buy_process.setHorizontalScrollBarEnabled(false);

        WebSettings settings = sdv_buy_process.getSettings();
        //支持缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //设置网页缓存
        settings.setAppCacheEnabled(true);// 开启缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 缓存优先模式
        settings.setAppCacheMaxSize(8 * 1024 * 1024);// 设置最大缓存为8M

        //支持javascript
        settings.setJavaScriptEnabled(true);
    }

    @Override
    public void onClick(View view) {

    }
}
