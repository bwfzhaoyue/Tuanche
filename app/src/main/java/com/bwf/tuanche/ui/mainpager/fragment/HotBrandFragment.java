package com.bwf.tuanche.ui.mainpager.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.hotbrand.HotBrandResultBean;
import com.bwf.tuanche.ui.mainpager.fragment.adpter.HotBrandAdpter;

public class HotBrandFragment extends BaseFragment {

    private RecyclerView rev_hot_brand_frag;

    private HotBrandResultBean result;

    private HotBrandAdpter adpter;
    private String cityId;

    @Override
    protected int getResource() {
        return R.layout.fragment_hot_brand;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        rev_hot_brand_frag=findViewByIdNoCast(R.id.rev_hot_brand_frag);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }

    public void setResult(HotBrandResultBean result) {
        this.result = result;
        adpter=new HotBrandAdpter(this.getActivity());
        GridLayoutManager layoutManager=new GridLayoutManager(this.getActivity(),3);
        rev_hot_brand_frag.setLayoutManager(layoutManager);
        adpter.setResult(result);
        adpter.setCityId(cityId);
        rev_hot_brand_frag.setAdapter(adpter);
        adpter.notifyDataSetChanged();

    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
