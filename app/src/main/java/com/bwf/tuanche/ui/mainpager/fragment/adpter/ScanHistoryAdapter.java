package com.bwf.tuanche.ui.mainpager.fragment.adpter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.db.model.UserModel;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.HotModleAndBrandDetailsActivity;
import com.bwf.tuanche.ui.mainpager.entity.hotmodle.HotModleResult;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/24.
 * Description:Tuanche
 */
public class ScanHistoryAdapter extends BaseAdapter {

    private Activity activity;

    private List<HotModleResult> results;

    public ScanHistoryAdapter(Activity activity) {
        this.activity = activity;
        results = UserModel.getUserModel().queryAllCustomer();
    }

    @Override
    public int getCount() {
        if (results == null)
            return 0;
        if (results.size() > 20)
            return 20;
        return results.size();
    }

    @Override
    public Object getItem(int i) {
        return results == null ? null : results.get(results.size()-i-1);
    }

    @Override
    public long getItemId(int i) {
        return results.size()-i-1;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ScanHistoryViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ScanHistoryViewHolder();
            view = View.inflate(activity, R.layout.scan_history_adapter, null);
            viewHolder.sdv_scan = (SimpleDraweeView) view.findViewById(R.id.sdv_scan);
            viewHolder.tv_scan_brand = (TextView) view.findViewById(R.id.tv_scan_brand);
            viewHolder.tv_scan_price = (TextView) view.findViewById(R.id.tv_scan_price);
            viewHolder.ll_scan = (LinearLayout) view.findViewById(R.id.ll_scan);
            view.setTag(viewHolder);
        } else
            viewHolder = (ScanHistoryViewHolder) view.getTag();
        viewHolder.sdv_scan.setImageURI(results.get(results.size() - i - 1).logo);
        viewHolder.tv_scan_brand.setText(results.get(results.size() - i - 1).styleName);
        viewHolder.tv_scan_price.setText("指导价: " + results.get(results.size() - i - 1).factoryPrice);
        viewHolder.ll_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putString("brandId", results.get(results.size() - i - 1).brandId);
                bundle.putString("styleId", results.get(results.size() - i - 1).id);
                bundle.putString("cityId", "156");
                IntentUtils.openActivity(activity, HotModleAndBrandDetailsActivity.class, bundle);
            }
        });
        return view;
    }

    public class ScanHistoryViewHolder {

        public TextView tv_scan_price, tv_scan_brand;
        public SimpleDraweeView sdv_scan;
        public LinearLayout ll_scan;
    }
}
