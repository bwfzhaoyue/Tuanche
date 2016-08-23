package com.bwf.tuanche.ui.mainpager.fragment.adpter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.utils.IntentUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.ChooseCarActivity;
import com.bwf.tuanche.ui.mainpager.HotModleAndBrandDetailsActivity;
import com.bwf.tuanche.ui.mainpager.entity.hotbrand.HotBrandResultBean;
import com.bwf.tuanche.ui.mainpager.entity.hotbrand.ListHotBrand;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class HotBrandAdpter extends RecyclerView.Adapter<HotBrandAdpter.ViewHolder> {


    private Activity activity;
    private String cityId;

    public HotBrandAdpter(Activity activity) {
        this.activity = activity;
    }

    public HotBrandAdpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(activity, R.layout.activity_hot_brand_adpter, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.img_brand = (SimpleDraweeView) view.findViewById(R.id.img_brand);
        viewHolder.tv_brand_name = (TextView) view.findViewById(R.id.tv_brand_name);
        viewHolder.tv_login_num = (TextView) view.findViewById(R.id.tv_login_num);
        viewHolder.ll_hot_band = (LinearLayout) view.findViewById(R.id.ll_hot_band);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (position == result.size()) {
            holder.img_brand.setImageResource(R.mipmap.icon_more);
            holder.tv_brand_name.setText("更多");
            holder.tv_login_num.setVisibility(View.INVISIBLE);
        } else {
            ListHotBrand brand = result.get(position);
            holder.img_brand.setImageURI(brand.logo);
            holder.tv_brand_name.setText(brand.name);
            String num = "有<font color='red'>" + brand.baseNum + "</font>人报名";
            holder.tv_login_num.setText(Html.fromHtml(num));
        }
        holder.ll_hot_band.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("cityId", cityId);
                if (position == result.size()) {
                    IntentUtils.openActivity(activity, ChooseCarActivity.class, bundle);
                } else {
                    bundle.putInt("type", 2);
                    bundle.putString("firmbrandId", result.get(position).id);
                    IntentUtils.openActivity(activity, HotModleAndBrandDetailsActivity.class, bundle);
                }
            }
        });
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }


    public int getItemCount() {
        return result == null ? 0 : result.size() + 1;
    }

    private List<ListHotBrand> result;

    public void setResult(HotBrandResultBean result) {
        this.result = result.result.list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView img_brand;
        public TextView tv_brand_name;
        public TextView tv_login_num;
        public LinearLayout ll_hot_band;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
