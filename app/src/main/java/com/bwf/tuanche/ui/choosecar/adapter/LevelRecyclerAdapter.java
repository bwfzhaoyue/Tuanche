package com.bwf.tuanche.ui.choosecar.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framwork.image.ImageLoader;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.choosecar.entity.condition.BosBean;
import com.bwf.tuanche.ui.choosecar.entity.condition.LevelBean;
import com.bwf.tuanche.ui.choosecar.entity.condition.SeriesBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author zhaoyue
 * Description:排量
 */
public class LevelRecyclerAdapter extends RecyclerView.Adapter<LevelRecyclerAdapter.RecyclerViewHolder> {

    private Context context;

    private List<LevelBean> list;//级别数据源

    public LevelRecyclerAdapter(Context context) {
        this.context = context;
    }

    public LevelRecyclerAdapter(Context context, List<LevelBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<LevelBean> list) {
        this.list = list;
    }

    public List<LevelBean> getList() {
        return list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_level_recycler,null);
        RecyclerViewHolder viewHodler = new RecyclerViewHolder(view);
        viewHodler.tv_level = (TextView) view.findViewById(R.id.tv_level);
        viewHodler.rl_bgcolor_control = (RelativeLayout) view.findViewById(R.id.rl_bgcolor_control);
        return viewHodler;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.tv_level.setText(list.get(position).name);

        if (list.get(position).isSelected){
            holder.rl_bgcolor_control.setBackground(context.getResources().getDrawable(R.drawable.shape_redrect));
            holder.tv_level.setTextColor(context.getResources().getColor(R.color.title_red));
        }else {
            holder.rl_bgcolor_control.setBackground(context.getResources().getDrawable(R.drawable.shape_grayrect));
            holder.tv_level.setTextColor(context.getResources().getColor(R.color.black));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).isSelected = !list.get(position).isSelected;
                LevelRecyclerAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_level;

        public RelativeLayout rl_bgcolor_control;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }
}
