package com.bwf.tuanche.ui.choosecar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.framwork.base.BaseListAdpter;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.entity.carsofbrand.CarInfoBean;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * author zhaoyue
 * Description
 */
public class CarInfoAdapter extends BaseListAdpter<CarInfoBean,CarInfoAdapter.ViewHolder>{

    private ImageLoader imageLoader;

    public CarInfoAdapter(Context context) {
        super(context);
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getResourceId() {
        return R.layout.item_carinfo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.img_carlogo = findViewByIdNoCast(R.id.img_carlogo);
        viewHolder.tv_car_name = findViewByIdNoCast(R.id.tv_car_name);
        viewHolder.tv_car_zhidaojia = findViewByIdNoCast(R.id.tv_car_zhidaojia);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, CarInfoBean carInfoBean, int position) {
        imageLoader.disPlayImage(holder.img_carlogo,carInfoBean.logo);
        holder.tv_car_name.setText(carInfoBean.styleName);
        holder.tv_car_zhidaojia.setText(carInfoBean.pricePrefix+carInfoBean.factoryPrice);
    }

    public class ViewHolder extends BaseListAdpter.ViewHolder{
        public SimpleDraweeView img_carlogo;

        public TextView tv_car_name;

        public TextView tv_car_zhidaojia;
    }
}
