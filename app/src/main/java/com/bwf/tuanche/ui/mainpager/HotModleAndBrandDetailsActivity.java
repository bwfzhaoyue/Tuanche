package com.bwf.tuanche.ui.mainpager;

import android.content.Intent;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.ShareUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.cardetails.CarDetailsResultBean;
import com.bwf.tuanche.ui.mainpager.entity.cardetails.PromiseCar;
import com.bwf.tuanche.ui.mainpager.fragment.detailsfragment.BuyingCarEvaluteFragment;
import com.bwf.tuanche.ui.mainpager.fragment.detailsfragment.CarBuyingProcessFragment;
import com.bwf.tuanche.ui.mainpager.fragment.detailsfragment.CommenProblemFragment;
import com.bwf.tuanche.ui.mainpager.fragment.detailsfragment.ImageAndCustomerFragment;
import com.bwf.tuanche.ui.mainpager.fragment.detailsfragment.TuanChePromiseFragment;
import com.bwf.tuanche.view.LoadingView;
import com.bwf.tuanche.view.ObservableScrollView;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;

public class HotModleAndBrandDetailsActivity extends BaseActivity {

    private UMShareAPI shareAPI;

    private String cityId;

    private String styleId, brandId;  //热门车型

    private String firmbrandId;  //热门品牌

    private int type;

    private String shareUrl,shareImg,shareTitle;

    private ImageView img_retrun, img_share;
    private ImageAndCustomerFragment frag_img_customer;
    private TuanChePromiseFragment frag_tuanche_promise;
    private CarBuyingProcessFragment frag_tuanche_process;
    private BuyingCarEvaluteFragment frag_tuanche_evalute;
    private CommenProblemFragment frag_tuanche_commen_problem;
    private LoadingView runBoy_carDetail;
    private TextView tv_car_brand, tv_car_city;
    private Boolean runBoy = true;

    private ObservableScrollView scrollView_detail;//详情页ScrollView

    private Button bt_enter_in,bt_enter_bottom;//镶嵌到内容里和底部悬浮的两个报名按钮

    @Override
    public int getContentViewId() {
        return R.layout.activity_hot_modle_and_brand_details;
    }

    @Override
    public void beforeInitView() {
        type = getIntent().getIntExtra("type", 0);
        cityId = getIntent().getStringExtra("cityId");
        switch (type) {
            case 1: //热门车型
                styleId = getIntent().getStringExtra("styleId");
                brandId = getIntent().getStringExtra("brandId");
                getHotModleData();
                break;
            case 2://热门品牌
                firmbrandId = getIntent().getStringExtra("firmbrandId");
                getHotBanderData();
                break;
        }
        shareAPI = UMShareAPI.get(this);
    }

    @Override
    public void initView() {
        img_retrun = findViewByIdNoCast(R.id.img_retrun);
        runBoy_carDetail = findViewByIdNoCast(R.id.runBoy_carDetail);
        tv_car_city = findViewByIdNoCast(R.id.tv_car_city);
        tv_car_brand = findViewByIdNoCast(R.id.tv_car_brand);
        img_share = findViewByIdNoCast(R.id.img_share);
        scrollView_detail = findViewByIdNoCast(R.id.scrollView_detail);
        bt_enter_in = findViewByIdNoCast(R.id.bt_enter_in);
        bt_enter_bottom = findViewByIdNoCast(R.id.bt_enter_bottom);
        setOnClick(bt_enter_in,bt_enter_bottom);
        setToBack(img_retrun);
        frag_img_customer = (ImageAndCustomerFragment) getSupportFragmentManager().findFragmentById(R.id.frag_img_customer);
        frag_tuanche_promise = (TuanChePromiseFragment) getSupportFragmentManager().findFragmentById(R.id.frag_tuanche_promise);
        frag_tuanche_process = (CarBuyingProcessFragment) getSupportFragmentManager().findFragmentById(R.id.frag_tuanche_process);
        frag_tuanche_evalute = (BuyingCarEvaluteFragment) getSupportFragmentManager().findFragmentById(R.id.frag_tuanche_evalute);
        frag_tuanche_commen_problem = (CommenProblemFragment) getSupportFragmentManager().findFragmentById(R.id.frag_tuanche_commen_problem);
    }

    @Override
    public void initData() {
        tv_car_city.setText("成都站");
        setOnClick(img_share);

        scrollView_detail.setOnScrollChangeListener(new ObservableScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                Rect scrollBounds = new Rect();
                scrollView_detail.getHitRect(scrollBounds);

                    if (bt_enter_in.getLocalVisibleRect(scrollBounds)) {
                        //子控件至少有一个像素在可视范围内
                        bt_enter_bottom.setVisibility(View.GONE);
                    } else {
                        //子控件完全不在可视范围内
                        bt_enter_bottom.setVisibility(View.VISIBLE);

                    }


            }
        });

        scrollView_detail.setOnTouchListener(new View.OnTouchListener(){

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction()== MotionEvent.ACTION_MOVE){
//                        Rect scrollBounds = new Rect();
//                        scrollView_detail.getHitRect(scrollBounds);
//                        if (bt_enter_in.getLocalVisibleRect(scrollBounds)) {
//                            //子控件至少有一个像素在可视范围内
//                            bt_enter_bottom.setVisibility(View.GONE);
//                        } else {
//                            //子控件完全不在可视范围内
//                            bt_enter_bottom.setVisibility(View.VISIBLE);
//                        }
                    }
                    return false;
                }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_share:
                share(SHARE_MEDIA.QQ,shareTitle," ",shareUrl,shareImg);
                break;
        }
    }

    /**
     * 热门品牌
     */
    public void getHotBanderData() {
        HttpHelper.getCarDetailFromHotBand(firmbrandId, cityId, new HttpCallBack<CarDetailsResultBean>() {

            @Override
            public void onSuccess(CarDetailsResultBean result) {
                if (result != null) {
                    frag_img_customer.setResult(result);
                    LogUtils.e("", result.toString());
                    shareUrl=result.result.shareBrandUrl;
                    shareImg=result.result.logo;
                    frag_tuanche_evalute.setResult(result.result);
                    tv_car_brand.setText(result.result.styleName + "-");
                    String tcbzDesc = result.result.tcbzDesc;
                    List<PromiseCar> carList = JSON.parseArray(tcbzDesc.replace("\\", ""), PromiseCar.class);
                    if (carList != null)
                        frag_tuanche_promise.setResult(carList);
                    if (runBoy) {
                        runBoy_carDetail.setLoadingFinish();
                        runBoy = false;
                    }
                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
                if (runBoy) {
                    runBoy_carDetail.setLoadFail();
                    runBoy = false;
                }
            }
        });
    }


    /**
     * 热门车型
     */
    public void getHotModleData() {
        HttpHelper.getCarDetailFromHotModle(styleId, brandId, cityId, new HttpCallBack<CarDetailsResultBean>() {

            @Override
            public void onSuccess(CarDetailsResultBean result) {
                if (result != null) {
                    LogUtils.e("result:", result.toString());
                    shareUrl=result.result.shareStyleUrl;
                    LogUtils.e(shareUrl);
                    shareImg=result.result.brandLogo;
                    shareTitle=result.result.shareStyleTitle;
                    frag_img_customer.setResult(result);
                    tv_car_brand.setText(result.result.styleName + "-");
                    frag_tuanche_evalute.setResult(result.result);
                    String tcbzDesc = result.result.tcbzDesc;
                    List<PromiseCar> carList = JSON.parseArray(tcbzDesc.replace("\\", ""), PromiseCar.class);
                    if (carList != null)
                        frag_tuanche_promise.setResult(carList);
                    if (runBoy) {
                        runBoy_carDetail.setLoadingFinish();
                        runBoy = false;
                    }

                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
                if (runBoy) {
                    runBoy_carDetail.setLoadFail();
                    runBoy = false;
                }
            }
        });

    }


    public void share(SHARE_MEDIA share_media, String title, String desc
            , String url, String imageUrl) {
        ShareUtils.ThirdShare(this, share_media, title, desc, url, imageUrl, new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA share_media) {
                Toast.makeText(HotModleAndBrandDetailsActivity.this, "分享成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                Toast.makeText(HotModleAndBrandDetailsActivity.this, "分享失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                Toast.makeText(HotModleAndBrandDetailsActivity.this, "取消分享", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
