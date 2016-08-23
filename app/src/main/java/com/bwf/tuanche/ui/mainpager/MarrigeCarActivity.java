package com.bwf.tuanche.ui.mainpager;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpCallBack;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.framwork.utils.ListViewUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.marrigecar.MarrigeCarResultBean;
import com.bwf.tuanche.ui.mainpager.fragment.adpter.MarrigeCarAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

public class MarrigeCarActivity extends BaseActivity {

    private SimpleDraweeView sdv_marrige_title;
    private MarrigeCarAdapter carAdapter;

    private ListView lv_marrige;

    private MarrigeCarResultBean resultM;

    private ImageView img_back_marrige, img_share_marrige;

    private TextView tv_marrige;

    @Override
    public int getContentViewId() {
        return R.layout.activity_marrige_car;
    }

    @Override
    public void beforeInitView() {
        getMarrigeCar();
    }

    @Override
    public void initView() {
        tv_marrige = findViewByIdNoCast(R.id.tv_marrige);
        img_back_marrige = findViewByIdNoCast(R.id.img_back_marrige);
        img_share_marrige = findViewByIdNoCast(R.id.img_share_marrige);
        lv_marrige = findViewByIdNoCast(R.id.lv_marrige);
        sdv_marrige_title = findViewByIdNoCast(R.id.sdv_marrige_title);
        setToBack(img_back_marrige);
    }

    @Override
    public void initData() {
        if (resultM != null) {
            tv_marrige.setText(resultM.result.adpTitle);
            ImageLoader.getInstance().disPlayImage(sdv_marrige_title,resultM.result.adpLogo);
        }
    }

    @Override
    public void onClick(View view) {

    }

    public void getMarrigeCar() {
        HttpHelper.getMarrigeCar(new HttpCallBack<MarrigeCarResultBean>() {

            @Override
            public void onSuccess(MarrigeCarResultBean result) {
                if (result != null) {
                    carAdapter = new MarrigeCarAdapter(MarrigeCarActivity.this);
                    resultM = result;
                    carAdapter.settList(result.result.carStyleList);
                    initData();
                    lv_marrige.setAdapter(carAdapter);
                    ListViewUtils.measureListViewHeight(lv_marrige);
                    carAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }
}
