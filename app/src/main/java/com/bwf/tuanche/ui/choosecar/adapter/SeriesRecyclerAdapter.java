package com.bwf.tuanche.ui.choosecar.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framwork.image.ImageLoader;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.entity.condition.BosBean;
import com.bwf.tuanche.ui.choosecar.entity.condition.SeriesBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author zhaoyue
 * Description
 */
public class SeriesRecyclerAdapter extends RecyclerView.Adapter<SeriesRecyclerAdapter.RecyclerViewHolder> {

    private Context context;

    private List<SeriesBean> list;//级别数据源

    private ImageLoader imageLoader;

    {
        imageLoader = ImageLoader.getInstance();
    }

    public SeriesRecyclerAdapter(Context context) {
        this.context = context;
    }

    public SeriesRecyclerAdapter(Context context, List<SeriesBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<SeriesBean> list) {
        this.list = list;
    }

    public List<SeriesBean> getList() {
        return list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_series_recycler,null);
        RecyclerViewHolder viewHodler = new RecyclerViewHolder(view);
        viewHodler.img_country = (SimpleDraweeView) view.findViewById(R.id.img_country);
        viewHodler.tv_country = (TextView) view.findViewById(R.id.tv_country);
        viewHodler.rl_bgcolor_control = (RelativeLayout) view.findViewById(R.id.rl_bgcolor_control);
        return viewHodler;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        imageLoader.disPlayImage(holder.img_country,list.get(position).icon);

        holder.tv_country.setText(list.get(position).name);

        if (list.get(position).isSelected){
            holder.rl_bgcolor_control.setBackground(context.getResources().getDrawable(R.drawable.shape_redrect));
            holder.tv_country.setTextColor(context.getResources().getColor(R.color.title_red));
        }else {
            holder.rl_bgcolor_control.setBackground(context.getResources().getDrawable(R.drawable.shape_grayrect));
            holder.tv_country.setTextColor(context.getResources().getColor(R.color.black));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).isSelected = !list.get(position).isSelected;
                SeriesRecyclerAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView img_country;

        public TextView tv_country;

        public RelativeLayout rl_bgcolor_control;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }
}
