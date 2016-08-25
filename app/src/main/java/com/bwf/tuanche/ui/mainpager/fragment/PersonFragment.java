package com.bwf.tuanche.ui.mainpager.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.login.LoginActivity;
import com.bwf.tuanche.ui.mainpager.ScanHistoryActivity;

public class PersonFragment extends BaseFragment {

    private LinearLayout scan_history;

    private TextView tv_tologin;

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
        tv_tologin = findViewByIdNoCast(R.id.tv_tologin);
    }

    @Override
    protected void initData() {
        setOnClick(scan_history,tv_tologin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scan_history:
                IntentUtils.openActivity(getActivity(), ScanHistoryActivity.class);
                break;
            case R.id.tv_tologin:
                IntentUtils.openActivity(getActivity(), LoginActivity.class);
                break;
        }
    }
}
