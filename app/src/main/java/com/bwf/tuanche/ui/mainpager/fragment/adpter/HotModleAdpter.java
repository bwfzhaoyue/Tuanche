package com.bwf.tuanche.ui.mainpager.fragment.adpter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.db.model.UserModel;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.HotModleAndBrandDetailsActivity;
import com.bwf.tuanche.ui.mainpager.entity.hotmodle.HotModleResult;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/17.
 * Description:Tuanche
 */
public class HotModleAdpter extends RecyclerView.Adapter<HotModleAdpter.HotModleViewHolder>{

    private Context context;

    private List<HotModleResult> results;

    public HotModleAdpter(Context context) {
        this.context = context;
    }

    public void setResults(List<HotModleResult> results) {
        this.results = results;
    }

    @Override
    public HotModleAdpter.HotModleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=View.inflate(context, R.layout.hot_modle_adapter,null);
        HotModleViewHolder viewHolder=new HotModleViewHolder(view);
        viewHolder.sdv_car= (SimpleDraweeView) view.findViewById(R.id.sdv_car);
        viewHolder.tv_car_brand= (TextView) view.findViewById(R.id.tv_car_brand);
        viewHolder.tv_per_num= (TextView) view.findViewById(R.id.tv_per_num);
        viewHolder.tv_car_price= (TextView) view.findViewById(R.id.tv_car_price);
        viewHolder.ll_hot_modle= (LinearLayout) view.findViewById(R.id.ll_hot_modle);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HotModleViewHolder holder, final int position) {
        final HotModleResult result=results.get(position);
        holder.sdv_car.setImageURI(result.logo);
        holder.tv_car_brand.setText(result.styleName);
        String num="已有<font color='red'>"+result.manNum+"</font>人报名";
        holder.tv_per_num.setText( Html.fromHtml(num));
        holder.tv_car_price.setText("指导价:  "+result.factoryPrice);
        holder.ll_hot_modle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel.getUserModel().insertUser(results.get(position));
                Bundle bundle=new Bundle();
                bundle.putInt("type",1);
                bundle.putString("brandId",results.get(position).brandId);
                bundle.putString("styleId",results.get(position).id);
                bundle.putString("cityId","156");
                IntentUtils.openActivity(context, HotModleAndBrandDetailsActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results==null?0:results.size();
    }
    public class HotModleViewHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView sdv_car;

        private TextView tv_car_brand,tv_per_num,tv_car_price;

        private LinearLayout ll_hot_modle;
        public HotModleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
