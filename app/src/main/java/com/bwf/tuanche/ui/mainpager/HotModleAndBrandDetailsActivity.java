package com.bwf.tuanche.ui.mainpager;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.cardetails.CarDetailsResultBean;
import com.bwf.tuanche.ui.mainpager.entity.cardetails.PromiseCar;
import com.bwf.tuanche.ui.mainpager.fragment.detailsfragment.BuyingCarEvaluteFragment;
import com.bwf.tuanche.ui.mainpager.fragment.detailsfragment.CarBuyingProcessFragment;
import com.bwf.tuanche.ui.mainpager.fragment.detailsfragment.CommenProblemFragment;
import com.bwf.tuanche.ui.mainpager.fragment.detailsfragment.ImageAndCustomerFragment;
import com.bwf.tuanche.ui.mainpager.fragment.detailsfragment.TuanChePromiseFragment;

import java.util.List;

public class HotModleAndBrandDetailsActivity extends BaseActivity {

    private String cityId;

    private String styleId, brandId;  //热门车型

    private String firmbrandId;  //热门品牌

    private int type;

    private ImageView img_retrun;
    private ImageAndCustomerFragment frag_img_customer;
    private TuanChePromiseFragment frag_tuanche_promise;
    private CarBuyingProcessFragment frag_tuanche_process;
    private BuyingCarEvaluteFragment frag_tuanche_evalute;
    private CommenProblemFragment frag_tuanche_commen_problem;

    private TextView tv_car_brand,tv_car_city;

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
    }

    @Override
    public void initView() {
        img_retrun=findViewByIdNoCast(R.id.img_retrun);
        tv_car_city=findViewByIdNoCast(R.id.tv_car_city);
        tv_car_brand=findViewByIdNoCast(R.id.tv_car_brand);
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
    }

    @Override
    public void onClick(View view) {

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
                    frag_tuanche_evalute.setResult( result.result);
                    tv_car_brand.setText(result.result.styleName+"-");
                    String tcbzDesc = result.result.tcbzDesc;
                    List<PromiseCar> carList = JSON.parseArray(tcbzDesc.replace("\\",""),PromiseCar.class);
                    if (carList != null)
                        frag_tuanche_promise.setResult(carList);
                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
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
                    frag_img_customer.setResult(result);
                    frag_tuanche_evalute.setResult(result.result);
                    String tcbzDesc = result.result.tcbzDesc;
                    List<PromiseCar> carList = JSON.parseArray(tcbzDesc.replace("\\",""),PromiseCar.class);
                    if (carList != null)
                        frag_tuanche_promise.setResult(carList);

                }
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showToast(errMsg);
            }
        });

    }
}
