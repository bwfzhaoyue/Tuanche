package com.bwf.tuanche.ui.mainpager.fragment.detailsfragment;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.cardetails.CarDetailsResultBean;
import com.facebook.drawee.view.SimpleDraweeView;


public class ImageAndCustomerFragment extends BaseFragment {

    private SimpleDraweeView sdv_details_car;

    private TextView tv_this_num, tv_this_save_money, tv_tuan_time, tv_tuan_address, tv_tuan_price;

    private EditText et_customer_phone, et_customer_name;


    @Override
    protected int getResource() {
        return R.layout.fragment_image_and_customer;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        tv_this_num = findViewByIdNoCast(R.id.tv_this_num);
        tv_this_save_money = findViewByIdNoCast(R.id.tv_this_save_money);
        tv_tuan_time = findViewByIdNoCast(R.id.tv_tuan_time);
        tv_tuan_address = findViewByIdNoCast(R.id.tv_tuan_address);
        tv_tuan_price = findViewByIdNoCast(R.id.tv_tuan_price);

        sdv_details_car = findViewByIdNoCast(R.id.sdv_details_car);
        et_customer_phone = findViewByIdNoCast(R.id.et_customer_phone);
        et_customer_name = findViewByIdNoCast(R.id.et_customer_name);

    }

    @Override
    protected void initData() {
        if (result != null) {
            ImageLoader.getInstance().disPlayImage(sdv_details_car,result.result.logo);
            tv_this_num.setText(result.result.manNum+"人");
            tv_this_save_money.setText(result.result.saveUpMoney);
            tv_tuan_time.setText(result.result.groupbuyDate+"("+result.result.groupbuyWeek+")");
            tv_tuan_address.setText("成都"+result.result.regular4sShop);
        }
    }

    @Override
    public void onClick(View view) {

    }

    private CarDetailsResultBean result;

    public void setResult(CarDetailsResultBean result) {
        this.result = result;
        initData();
    }
}
