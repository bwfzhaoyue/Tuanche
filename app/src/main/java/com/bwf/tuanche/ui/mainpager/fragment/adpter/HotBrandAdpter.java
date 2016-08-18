package com.bwf.tuanche.ui.mainpager.fragment.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.hotbrand.HotBrandResultBean;
import com.bwf.tuanche.ui.mainpager.entity.hotbrand.ListHotBrand;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class HotBrandAdpter extends RecyclerView.Adapter<HotBrandAdpter.ViewHolder> {


    private Context context;


    public HotBrandAdpter(Context context) {
        this.context = context;
    }

    public HotBrandAdpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.activity_hot_brand_adpter, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.img_brand = (SimpleDraweeView) view.findViewById(R.id.img_brand);
        viewHolder.tv_brand_name = (TextView) view.findViewById(R.id.tv_brand_name);
        viewHolder.tv_login_num = (TextView) view.findViewById(R.id.tv_login_num);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        if (position == result.size()+1) {
//            holder.img_brand.setImageResource(R.mipmap.icon_more);
//            holder.tv_brand_name.setText("更多");
//            holder.tv_login_num.setVisibility(View.INVISIBLE);
//        } else {
            ListHotBrand brand = result.get(position);
            holder.img_brand.setImageURI(brand.logo);
            holder.tv_brand_name.setText(brand.name);
            String num="已有<font color='red'>"+brand.baseNum+"</font>人报名";
            holder.tv_login_num.setText( Html.fromHtml(num));
//            holder.tv_login_num.setText(String.format(context.getString(R.string.login_num), brand.baseNum));
//        }
    }


    public int getItemCount() {
        return result == null ? 0 : result.size() ;
    }

    private List<ListHotBrand> result;

    public void setResult(HotBrandResultBean result) {
        this.result = result.result.list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView img_brand;
        public TextView tv_brand_name;
        public TextView tv_login_num;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
