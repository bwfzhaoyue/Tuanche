package com.bwf.tuanche.ui.mainpager.fragment.detailsfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.EvaluteDetailsActivity;
import com.bwf.tuanche.ui.mainpager.entity.cardetails.CarDetailsResult;
import com.bwf.tuanche.ui.mainpager.fragment.adpter.BuyingCarEvaluteAdapter;

/**
 * Created by zengqiang on 2016/8/18.
 * Description:Tuanche
 */
public class BuyingCarEvaluteFragment extends BaseFragment {

    private RecyclerView lv_evalute;

    private TextView tv_evalute_score;

    private RatingBar ratingBar;

    private LinearLayout ll_evlute_all;

    private TextView tv_num_evalute;

    private BuyingCarEvaluteAdapter adapter;

    @Override
    protected int getResource() {
        return R.layout.buying_car_evalute_fragment;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        ratingBar=findViewByIdNoCast(R.id.ratingBar);
        tv_evalute_score=findViewByIdNoCast(R.id.tv_evalute_score);
        lv_evalute=findViewByIdNoCast(R.id.lv_evalute);
        tv_num_evalute=findViewByIdNoCast(R.id.tv_num_evalute);
        ll_evlute_all=findViewByIdNoCast(R.id.ll_evlute_all);

    }

    @Override
    protected void initData() {

        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_evalute.setLayoutManager(layoutManager);
        setOnClick(ll_evlute_all);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ll_evlute_all:
                Bundle bundle=new Bundle();
                bundle.putString("brandId",result.brandId);
                IntentUtils.openActivity(getActivity(), EvaluteDetailsActivity.class,bundle);
                break;
        }

    }
    private CarDetailsResult result;
    public void setResult(CarDetailsResult result) {
        this.result = result;
        adapter=new BuyingCarEvaluteAdapter(getActivity());
        adapter.setCommentLists(result.comment.commentList);
        lv_evalute.setAdapter(adapter);
//        ListViewUtils.measureListViewHeight(lv_evalute);
        adapter.notifyDataSetChanged();
        tv_num_evalute.setText("查看全部"+result.comment.count+"人评论");
        tv_evalute_score.setText(result.commentTotal+"分");
        ratingBar.setRating((float) result.commentTotal);
    }
}
