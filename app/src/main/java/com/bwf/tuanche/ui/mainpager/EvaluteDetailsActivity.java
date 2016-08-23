package com.bwf.tuanche.ui.mainpager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.evalutedetails.EvaluteResultBean;
import com.bwf.tuanche.ui.mainpager.fragment.adpter.BuyingCarEvaluteAdapter;

public class EvaluteDetailsActivity extends BaseActivity {

    private RecyclerView lv_evalute_detail;

    private TextView tv_4s_service,tv_service,tv_evalute_price,tv_evalute_detail_score;

    private RatingBar ratingBar_evalute_details;

    private ImageView img_evalute_details_back;

    private BuyingCarEvaluteAdapter adapter;

    private int count=10;
    private int offset=1;

    private String cityId="156";

    private String brandId;

    @Override
    public int getContentViewId() {
        return R.layout.activity_evalute_details;
    }

    @Override
    public void beforeInitView() {
        brandId=getIntent().getStringExtra("brandId");
        getData();
    }

    @Override
    public void initView() {
        lv_evalute_detail=findViewByIdNoCast(R.id.lv_evalute_detail);
        tv_4s_service=findViewByIdNoCast(R.id.tv_4s_service);
        tv_service=findViewByIdNoCast(R.id.tv_service);
        tv_evalute_price=findViewByIdNoCast(R.id.tv_evalute_price);
        tv_evalute_detail_score=findViewByIdNoCast(R.id.tv_evalute_detail_score);
        ratingBar_evalute_details=findViewByIdNoCast(R.id.ratingBar_evalute_details);
        img_evalute_details_back=findViewByIdNoCast(R.id.img_evalute_details_back);

    }

    @Override
    public void initData() {
        setToBack(img_evalute_details_back);
        LinearLayoutManager layoutMannager=new LinearLayoutManager(this);
        layoutMannager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_evalute_detail.setLayoutManager(layoutMannager);
        adapter=new BuyingCarEvaluteAdapter(this);

    }

    @Override
    public void onClick(View view) {

    }

    public void getData(){

        HttpHelper.getAllEvalute(count+"", offset+"", cityId, brandId, new HttpCallBack<EvaluteResultBean>() {

            @Override
            public void onSuccess(EvaluteResultBean result) {
                if (result!=null){
                    ratingBar_evalute_details.setRating((float) result.result.commentTotal);
                    tv_evalute_price.setText(result.result.priceScore+"分");
                    tv_service.setText(result.result.salerScore+"分");
                    tv_4s_service.setText(result.result.shopScore+"分");

                    adapter.setCommentLists(result.result.commentList);
                    lv_evalute_detail.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }
}
