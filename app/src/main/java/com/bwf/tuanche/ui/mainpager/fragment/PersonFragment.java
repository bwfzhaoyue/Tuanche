package com.bwf.tuanche.ui.mainpager.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.ScanHistoryActivity;

public class PersonFragment extends BaseFragment {

    private LinearLayout scan_history;

    @Override
    protected int getResource() {
        return R.layout.fragment_person;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        scan_history = findViewByIdNoCast(R.id.scan_history);
    }

    @Override
    protected void initData() {
        setOnClick(scan_history);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scan_history:
                IntentUtils.openActivity(getActivity(), ScanHistoryActivity.class);
                break;
        }
    }
}
