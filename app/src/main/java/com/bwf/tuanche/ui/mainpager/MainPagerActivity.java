package com.bwf.tuanche.ui.mainpager;

import android.view.View;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.base.BaseBean;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.tuanche.R;

public class MainPagerActivity extends BaseActivity {


    public int getContentViewId() {
        return R.layout.activity_main_pager;
    }

    @Override
    public void beforeInitView() {
        getTopBrandData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    public void getTopBrandData() {
        HttpHelper.getTopBrand("156",new HttpCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                LogUtils.e("tag"+result.toString());
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }
}
