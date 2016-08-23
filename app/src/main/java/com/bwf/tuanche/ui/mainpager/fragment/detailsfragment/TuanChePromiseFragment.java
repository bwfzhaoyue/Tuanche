package com.bwf.tuanche.ui.mainpager.fragment.detailsfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.cardetails.PromiseCar;
import com.bwf.tuanche.ui.mainpager.fragment.adpter.TuanchePromiseAdapter;

import java.util.List;


public class TuanChePromiseFragment extends BaseFragment {

    private TuanchePromiseAdapter adapter;
    private RecyclerView rec_tuanche_promise;
    @Override
    protected int getResource() {
        return R.layout.fragment_tuan_che_promise;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        rec_tuanche_promise=findViewByIdNoCast(R.id.rec_tuanche_promise);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        rec_tuanche_promise.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
    private List<PromiseCar> result;
    public void setResult(List<PromiseCar> result) {
        this.result = result;
        adapter=new TuanchePromiseAdapter(this.getActivity());
        adapter.setResult(result);
        rec_tuanche_promise.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
