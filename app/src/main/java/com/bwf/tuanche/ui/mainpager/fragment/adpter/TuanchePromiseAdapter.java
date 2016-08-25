package com.bwf.tuanche.ui.mainpager.fragment.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.cardetails.PromiseCar;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/18.
 * Description:Tuanche
 */
public class TuanchePromiseAdapter extends RecyclerView.Adapter<TuanchePromiseAdapter.TuanchePromiseViewHolder> {

    private Context context;

    public TuanchePromiseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TuanchePromiseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.tuanchepromiseadapter, null);
        TuanchePromiseViewHolder viewHolder = new TuanchePromiseViewHolder(view);
        viewHolder.sdv_promise = (SimpleDraweeView) view.findViewById(R.id.sdv_promise);
        viewHolder.tv_promise_title = (TextView) view.findViewById(R.id.tv_promise_title);
        viewHolder.tv_promise_desc = (TextView) view.findViewById(R.id.tv_promise_desc);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TuanchePromiseViewHolder holder, int position) {
        holder.sdv_promise.setImageURI(result.get(position).imgurl);
        holder.tv_promise_title.setText(result.get(position).title);
        holder.tv_promise_desc.setText(result.get(position).describe);
    }


    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
    }

    private List<PromiseCar> result;

    public void setResult(List<PromiseCar> result) {
        this.result = result;
    }

    public class TuanchePromiseViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView sdv_promise;

        public TextView tv_promise_title, tv_promise_desc;

        public TuanchePromiseViewHolder(View itemView) {
            super(itemView);
        }
    }
}
