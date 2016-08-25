package com.bwf.tuanche.ui.mainpager;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.framwork.utils.ListViewUtils;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.ShareUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.marrigecar.MarrigeCarResultBean;
import com.bwf.tuanche.ui.mainpager.fragment.adpter.MarrigeCarAdapter;
import com.bwf.tuanche.view.LoadingView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class MarrigeCarActivity extends BaseActivity {

    private SimpleDraweeView sdv_marrige_title;
    private MarrigeCarAdapter carAdapter;

    private ListView lv_marrige;

    private MarrigeCarResultBean resultM;

    private ImageView img_back_marrige, img_share_marrige;

    private TextView tv_marrige;

    private LoadingView runBoy_marrige;

    private String shareUrl,shareImg,shareTitle;
    @Override
    public int getContentViewId() {
        return R.layout.activity_marrige_car;
    }

    @Override
    public void beforeInitView() {
        getMarrigeCar();
    }

    @Override
    public void initView() {
        tv_marrige = findViewByIdNoCast(R.id.tv_marrige);
        img_back_marrige = findViewByIdNoCast(R.id.img_back_marrige);
        img_share_marrige = findViewByIdNoCast(R.id.img_share_marrige);
        lv_marrige = findViewByIdNoCast(R.id.lv_marrige);
        sdv_marrige_title = findViewByIdNoCast(R.id.sdv_marrige_title);
        runBoy_marrige = findViewByIdNoCast(R.id.runBoy_marrige);
        setToBack(img_back_marrige);
    }

    @Override
    public void initData() {
        setOnClick(img_share_marrige);
        if (resultM != null) {
            tv_marrige.setText(resultM.result.adpTitle);
            ImageLoader.getInstance().disPlayImage(sdv_marrige_title, resultM.result.adpLogo);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_share_marrige:
                share(SHARE_MEDIA.QQ,shareTitle,"  ",shareUrl,shareImg);
                break;
        }
    }

    public void getMarrigeCar() {
        HttpHelper.getMarrigeCar(new HttpCallBack<MarrigeCarResultBean>() {

            @Override
            public void onSuccess(MarrigeCarResultBean result) {
                if (result != null) {
                    shareImg=result.result.sharePic;
                    shareUrl=result.result.shareUrl;
                    LogUtils.e(shareUrl);
                    shareTitle=result.result.shareSlogan;
                    carAdapter = new MarrigeCarAdapter(MarrigeCarActivity.this);
                    resultM = result;
                    carAdapter.settList(result.result.carStyleList);
                    initData();
                    lv_marrige.setAdapter(carAdapter);
                    ListViewUtils.measureListViewHeight(lv_marrige);
                    carAdapter.notifyDataSetChanged();
                    if (runBoy) {
                        runBoy_marrige.setLoadingFinish();
                        runBoy = false;
                    }
                }
            }

            @Override
            public void onFail(String errMsg) {
                if (runBoy) {
                    runBoy_marrige.setLoadFail();
                    runBoy = false;
                }
            }
        });
    }

    private boolean runBoy = true;

    public void share(SHARE_MEDIA share_media, String title, String desc
            , String url, String imageUrl) {
        ShareUtils.ThirdShare(this, share_media, title, desc, url, imageUrl, new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA share_media) {
                Toast.makeText(MarrigeCarActivity.this, "分享成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                Toast.makeText(MarrigeCarActivity.this, "分享失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                Toast.makeText(MarrigeCarActivity.this, "取消分享", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
