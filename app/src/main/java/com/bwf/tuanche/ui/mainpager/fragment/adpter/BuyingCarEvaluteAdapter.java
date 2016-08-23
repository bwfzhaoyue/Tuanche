package com.bwf.tuanche.ui.mainpager.fragment.adpter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bwf.framwork.image.ImageLoader;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.cardetails.CommentList;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/18.
 * Description:Tuanche
 */
public class BuyingCarEvaluteAdapter extends RecyclerView.Adapter<BuyingCarEvaluteAdapter.BuyingCarEvaluteViewHolder> {

    private Activity context;

    private ListView lv_evalute;

    private Img3_Recycler_Adapter adapter;

    private GridLayoutManager layoutManager;


    public BuyingCarEvaluteAdapter(Activity context) {
        this.context = context;
        adapter = new Img3_Recycler_Adapter(context);
        layoutManager = new GridLayoutManager(context, 3);
    }


    @Override
    public BuyingCarEvaluteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.buyingcar_evalute_adapter, null);
        BuyingCarEvaluteViewHolder viewHolder = new BuyingCarEvaluteViewHolder(view);
        viewHolder.img_shrink = (ImageView) view.findViewById(R.id.img_shrink);
        viewHolder.sdv_head_portrait = (SimpleDraweeView) view.findViewById(R.id.sdv_head_portrait);
        viewHolder.tv_name_per = (TextView) view.findViewById(R.id.tv_name_per);
        viewHolder.tv_per_content = (TextView) view.findViewById(R.id.tv_per_content);
        viewHolder.tv_say_time = (TextView) view.findViewById(R.id.tv_say_time);
        viewHolder.ratingBar_person = (RatingBar) view.findViewById(R.id.ratingBar_person);
        viewHolder.recycler_evalute_img = (RecyclerView) view.findViewById(R.id.recycler_evalute_img);
        viewHolder.img_renzhen_evalute = (ImageView) view.findViewById(R.id.img_renzhen_evalute);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BuyingCarEvaluteViewHolder viewHolder, final int i) {
        ImageLoader.getInstance().disPlayImage(viewHolder.sdv_head_portrait, reult.get(i).memberPic);
        viewHolder.tv_name_per.setText(reult.get(i).userName);
        viewHolder.tv_say_time.setText(reult.get(i).commentDate);
        viewHolder.tv_per_content.setText(reult.get(i).content);
        viewHolder.ratingBar_person.setRating(reult.get(i).score);
        if (reult.get(i).fine)
            viewHolder.img_renzhen_evalute.setVisibility(View.VISIBLE);
        else
            viewHolder.img_renzhen_evalute.setVisibility(View.GONE);

//        viewHolder.recycler_evalute_img.setLayoutManager(layoutManager);
        viewHolder.tv_per_content.postDelayed(new Runnable() {
            @Override
            public void run() {

                reult.get(i).lineCount = viewHolder.tv_per_content.getLineCount();
                reult.get(i).ok = false;
                if (!reult.get(i).ok && ok) {
                    if (reult.get(i).lineCount <= 3) {
                        viewHolder.img_shrink.setVisibility(View.GONE);
                        viewHolder.tv_per_content.setLines(reult.get(i).lineCount);
                    } else {
                        viewHolder.img_shrink.setVisibility(View.VISIBLE);
                        viewHolder.tv_per_content.setLines(3);
                    }
                }

            }
        }, 100);


        viewHolder.img_shrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reult.get(i).isDown) {
                    reult.get(i).isDown = false;
                } else {
                    reult.get(i).isDown = true;
                }
                notifyDataSetChanged();
                ok = false;
                reult.get(i).ok = true;
            }
        });

        if (viewHolder.img_shrink.getVisibility() == View.VISIBLE) {
            if (reult.get(i).isDown) {
                viewHolder.img_shrink.setImageResource(R.mipmap.down_arrows);
                viewHolder.tv_per_content.setLines(3);

            } else {
                viewHolder.img_shrink.setImageResource(R.mipmap.up_arrows_gray);
                viewHolder.tv_per_content.setLines(reult.get(i).lineCount);
            }
        }
    }


    @Override
    public int getItemCount() {
        return reult == null ? 0 : reult.size();
    }


    private boolean ok = true;
    private List<CommentList> reult;

    public void setCommentLists(List<CommentList> commentLists) {
        this.reult = commentLists;
    }

    public class BuyingCarEvaluteViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView sdv_head_portrait;

        public TextView tv_name_per, tv_say_time, tv_per_content;

        public ImageView img_shrink, img_renzhen_evalute;

        public RatingBar ratingBar_person;

        public RecyclerView recycler_evalute_img;

        public BuyingCarEvaluteViewHolder(View itemView) {
            super(itemView);
        }
    }
}
