package com.bwf.tuanche.ui.mainpager;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.fragment.adpter.ScanHistoryAdapter;

public class ScanHistoryActivity extends BaseActivity {
    private ImageView img_scan_back;
    private ListView lv_scan;

    private ScanHistoryAdapter adapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_scan_history;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        lv_scan=findViewByIdNoCast(R.id.lv_scan);
        img_scan_back=findViewByIdNoCast(R.id.img_scan_back);
        setToBack(img_scan_back);
    }

    @Override
    public void initData() {
        adapter=new ScanHistoryAdapter(this);
        lv_scan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

    }
}
