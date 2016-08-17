package com.bwf.tuanche.ui.mainpager.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.tuanche.R;

public class HotBrandFragment extends BaseFragment {

    private RecyclerView rev_hot_brand;
    @Override
    protected int getResource() {
        return R.layout.fragment_hot_brand;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        rev_hot_brand=findViewByIdNoCast(R.id.rev_hot_brand);
    }

    @Override
    protected void initData() {
        GridLayoutManager layoutManager=new GridLayoutManager(this.getActivity(),3);
        rev_hot_brand.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View view) {

    }
}
