package com.bwf.tuanche.ui.mainpager.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.hotmodle.HotModleResultBean;
import com.bwf.tuanche.ui.mainpager.fragment.adpter.HotModleAdpter;


public class HotModleFragment extends BaseFragment {

    private RecyclerView rev_hot_brand_frag;

    private HotModleResultBean result;

    private HotModleAdpter adpter;
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

    public void setResult(HotModleResultBean result) {
        this.result = result;
        adpter=new HotModleAdpter(this.getActivity());
        adpter.setResults(result.result);
        GridLayoutManager layoutManager=new GridLayoutManager(this.getActivity(),2);
        rev_hot_brand_frag.setLayoutManager(layoutManager);
        rev_hot_brand_frag.setAdapter(adpter);
        adpter.notifyDataSetChanged();
    }
}
