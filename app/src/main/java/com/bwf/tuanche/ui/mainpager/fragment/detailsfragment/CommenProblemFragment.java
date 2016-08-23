package com.bwf.tuanche.ui.mainpager.fragment.detailsfragment;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.tuanche.R;

public class CommenProblemFragment extends BaseFragment {

    private WebView sdv_commen_problem;

    private  String url;
    @Override
    protected int getResource() {
        return R.layout.fragment_commen_problem;
    }

    @Override
    protected void beforeInitView() {
         url="http://123.56.145.151:8080/TuanCheNetWork/bwf_TuanChe_BuyInfoQuestionServlet";

    }

    @Override
    protected void initView(View rootView) {
        sdv_commen_problem=findViewByIdNoCast(R.id.sdv_commen_problem);
    }

    @Override
    protected void initData() {
        sdv_commen_problem.loadUrl(url);
        //设置滚动条样式
        sdv_commen_problem.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        sdv_commen_problem.setHorizontalScrollBarEnabled(false);

        WebSettings settings = sdv_commen_problem.getSettings();
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
