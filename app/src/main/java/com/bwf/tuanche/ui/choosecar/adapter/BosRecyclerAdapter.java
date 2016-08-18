package com.bwf.tuanche.ui.choosecar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.framwork.image.ImageLoader;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.entity.condition.BosBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author zhaoyue
 * Description:条件选车：级别 adapter
 */
public class BosRecyclerAdapter extends RecyclerView.Adapter<BosRecyclerAdapter.RecyclerViewHolder> {

    private Context context;

    private List<BosBean> list;//级别数据源

    private ImageLoader imageLoader;

    {
        imageLoader = ImageLoader.getInstance();
    }

    public BosRecyclerAdapter(Context context) {
        this.context = context;
    }

    public BosRecyclerAdapter(Context context, List<BosBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<BosBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_bos_recycler,null);
        RecyclerViewHolder viewHodler = new RecyclerViewHolder(view);
        viewHodler.img_bos = (SimpleDraweeView) view.findViewById(R.id.img_bos);
        viewHodler.tv_bos = (TextView) view.findViewById(R.id.tv_bos);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        if (list.get(position).isSelected){
            imageLoader.disPlayImage(holder.img_bos,list.get(position).icon);
            holder.tv_bos.setTextColor(context.getResources().getColor(R.color.title_red));
        }else{
            imageLoader.disPlayImage(holder.img_bos,list.get(position).defIcon);
            holder.tv_bos.setTextColor(context.getResources().getColor(R.color.black));
        }
        holder.tv_bos.setText(list.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).isSelected = !list.get(position).isSelected;
                BosRecyclerAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView img_bos;

        public TextView tv_bos;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }
}
