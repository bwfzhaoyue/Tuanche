package com.bwf.tuanche.ui.choosecar.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.framwork.base.BaseListAdpter;
import com.bwf.tuanche.R;

/**
 * author zhaoyue
 * Description
 */
public class IndexAdapter extends BaseListAdpter<String,IndexAdapter.ViewHolder> {


    public IndexAdapter(Context context) {
        super(context);
    }



    @Override
    public int getResourceId() {
        return R.layout.item_index;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.tv_index = findViewByIdNoCast(R.id.tv_index);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, String s, int position) {
        holder.tv_index.setText(s);
    }

    public class ViewHolder extends BaseListAdpter.ViewHolder{
        public TextView tv_index;
    }
}
