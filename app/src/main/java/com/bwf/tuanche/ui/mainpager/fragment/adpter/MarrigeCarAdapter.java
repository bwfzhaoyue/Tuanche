package com.bwf.tuanche.ui.mainpager.fragment.adpter;

import android.content.Context;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.framwork.base.BaseListAdpter;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.marrigecar.CarStyleList;
import com.facebook.drawee.view.SimpleDraweeView;


/**
 * Created by zengqiang on 2016/8/19.
 * Description:Tuanche
 */
public class MarrigeCarAdapter extends BaseListAdpter<CarStyleList, MarrigeCarAdapter.MarrigeCarViewHolder> {

    private ImageLoader imageLoader;

    public MarrigeCarAdapter(Context context) {
        super(context);
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getResourceId() {
        return R.layout.marrige_car_adapter;
    }

    public MarrigeCarViewHolder onCreateViewHolder(ViewGroup parent) {
        MarrigeCarViewHolder viewHolder = new MarrigeCarViewHolder();
        viewHolder.sdv_car_marrige = findViewByIdNoCast(R.id.sdv_car_marrige);
        viewHolder.tv_marrige_car_brand = findViewByIdNoCast(R.id.tv_marrige_car_brand);
        viewHolder.tv_advice_price = findViewByIdNoCast(R.id.tv_advice_price);
        viewHolder.tv_adnum = findViewByIdNoCast(R.id.tv_adnum);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MarrigeCarViewHolder holder, CarStyleList carStyleList, int position) {
        imageLoader.disPlayImage(holder.sdv_car_marrige, carStyleList.logo);
        holder.tv_marrige_car_brand.setText(carStyleList.styleName);
        holder.tv_advice_price.setText(carStyleList.pricePrefix + carStyleList.price + carStyleList.priceSuffix);
        String num = "已有<font color='red'>" + carStyleList.manNum + "</font>人报名";
        holder.tv_adnum.setText(Html.fromHtml(num));
    }


    public class MarrigeCarViewHolder extends BaseListAdpter.ViewHolder {
        public SimpleDraweeView sdv_car_marrige;
        public TextView tv_marrige_car_brand, tv_advice_price, tv_adnum;
    }
}
