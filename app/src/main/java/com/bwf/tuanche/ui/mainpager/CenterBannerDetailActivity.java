package com.bwf.tuanche.ui.mainpager;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.tuanche.R;
import com.bwf.tuanche.view.LoadingView;

public class CenterBannerDetailActivity extends BaseActivity {

    private ImageView img_back_banner;
    private String shareUrl;
    private String adName;

    private WebView wb_banner_center;

    private TextView tv_banner_title;

    private LoadingView runBoy_centerBanner;

    private boolean runBoy=true;
    @Override

    public int getContentViewId() {
        return R.layout.activity_center_banner_detail;
    }

    @Override
    public void beforeInitView() {
        shareUrl=getIntent().getStringExtra("shareUrl");
        adName=getIntent().getStringExtra("adName");
    }

    @Override
    public void initView() {
        wb_banner_center=findViewByIdNoCast(R.id.wb_banner_center);
        tv_banner_title=findViewByIdNoCast(R.id.tv_banner_title);
        img_back_banner=findViewByIdNoCast(R.id.img_back_banner);
        runBoy_centerBanner=findViewByIdNoCast(R.id.runBoy_centerBanner);
        setToBack(img_back_banner);
    }

    @Override
    public void initData() {
       tv_banner_title.setText(adName);
        wb_banner_center.loadUrl(shareUrl);
        wb_banner_center.setWebViewClient(new MyWebViewClient());
        //设置滚动条样式
        wb_banner_center.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        wb_banner_center.setHorizontalScrollBarEnabled(false);
        WebSettings settings = wb_banner_center.getSettings();
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
    private boolean isFirst;
    public class MyWebViewClient extends WebViewClient {

        /**
         * 保证url在本页面跳转
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!isFirst) {
                isFirst = true;
                CenterBannerDetailActivity.this.shareUrl = url;
            }
            view.loadUrl(url);
            return false;
        }
        /**
         * 网页加载完成
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            if (runBoy){
                runBoy_centerBanner.setLoadingFinish();
                runBoy=false;
            }
            super.onPageFinished(view, url);
        }
    }


    /**
     * 重写返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {

            if (wb_banner_center.canGoBack()) {//如果网页可以回退
                wb_banner_center.goBack();
                return false;
            }

        }

        return super.onKeyDown(keyCode, event);
    }



}
