package com.bwf.tuanche.ui.mainpager.fragment.adpter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.framwork.utils.IntentUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.application.MyApplication;
import com.bwf.tuanche.ui.mainpager.Img_Pop;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

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
        View view = View.inflate(activity, R.layout.img3_recycler_adapter, null);
        Img3_Re_Ad_ViewHolder viewHolder = new Img3_Re_Ad_ViewHolder(view);
        viewHolder.sdv_img3_re_ad = (SimpleDraweeView) view.findViewById(R.id.sdv_img3_re_ad);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Img3_Re_Ad_ViewHolder holder, final int position) {
        holder.sdv_img3_re_ad.setImageURI(comlistPic.get(position));
        holder.sdv_img3_re_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putInt("position",position);
                MyApplication.getMyApplication().setPicLists(comlistPic);
                IntentUtils.openActivity(activity, Img_Pop.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comlistPic == null ? 0 : comlistPic.size();
    }

    private List<String> comlistPic;

    public void setComlistPic(List<String> comlistPic) {
        this.comlistPic = comlistPic;
    }

    public class Img3_Re_Ad_ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView sdv_img3_re_ad;

        public Img3_Re_Ad_ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
