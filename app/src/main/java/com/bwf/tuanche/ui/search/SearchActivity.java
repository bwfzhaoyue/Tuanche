package com.bwf.tuanche.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.tuanche.R;

public class SearchActivity extends BaseActivity {

    private RecyclerView recycler_searching,recycler_search_history,recycler_search;
    @Override
    public int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        recycler_searching=findViewByIdNoCast(R.id.recycler_searching);
        recycler_search_history=findViewByIdNoCast(R.id.recycler_search_history);
        recycler_search=findViewByIdNoCast(R.id.recycler_search);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
