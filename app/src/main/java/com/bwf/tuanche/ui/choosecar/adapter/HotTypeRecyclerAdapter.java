package com.bwf.tuanche.ui.choosecar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.framwork.image.ImageLoader;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.entity.hotcar.HotCarTypeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author zhaoyue
 * Description
 */
public class HotTypeRecyclerAdapter extends RecyclerView.Adapter<HotTypeRecyclerAdapter.RecyclerViewHolder> {

    private Context context;

    private List<HotCarTypeBean> list;

    private ImageLoader imageLoader;

    {
        imageLoader = ImageLoader.getInstance();
    }

    public HotTypeRecyclerAdapter(Context context) {
        this.context = context;
    }

    public HotTypeRecyclerAdapter(Context context, List<HotCarTypeBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<HotCarTypeBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_hottype_recycler,null);
        RecyclerViewHolder viewHodler = new RecyclerViewHolder(view);
        viewHodler.img_brand_logo = (SimpleDraweeView) view.findViewById(R.id.img_brand_logo);
        viewHodler.tv_brand_name = (TextView) view.findViewById(R.id.tv_brand_name);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        imageLoader.disPlayImage(holder.img_brand_logo,list.get(position).logo);
        holder.tv_brand_name.setText(list.get(position).name);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView img_brand_logo;

        public TextView tv_brand_name;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }
}
