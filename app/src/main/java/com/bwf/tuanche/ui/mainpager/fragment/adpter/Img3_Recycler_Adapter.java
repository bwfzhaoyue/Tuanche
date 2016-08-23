package com.bwf.tuanche.ui.mainpager.fragment.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.tuanche.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by zengqiang on 2016/8/23.
 * Description:Tuanche
 */
public class Img3_Recycler_Adapter extends RecyclerView.Adapter<Img3_Recycler_Adapter.Img3_Re_Ad_ViewHolder> {

    private Activity activity;

    public Img3_Recycler_Adapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Img3_Re_Ad_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(activity, R.layout.img3_recycler_adapter,null);
        Img3_Re_Ad_ViewHolder viewHolder=new Img3_Re_Ad_ViewHolder(view);
        viewHolder.sdv_img3_re_ad= (SimpleDraweeView) view.findViewById(R.id.sdv_img3_re_ad);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Img3_Re_Ad_ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Img3_Re_Ad_ViewHolder extends RecyclerView.ViewHolder  {
        public SimpleDraweeView sdv_img3_re_ad;

        public Img3_Re_Ad_ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
