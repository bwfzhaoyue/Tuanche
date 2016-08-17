package com.bwf.tuanche.ui.mainpager.fragment;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.entity.BannerResult;

public class BannerFragment extends BaseFragment {


    private ImageView img_center_banner1, img_center_banner2;

    private TextView tv_pos_banner_type1_1, tv_pos_banner_type1_2, tv_pos_banner_type2_1, tv_pos_banner_type2_2;

    private ImageView img_pos_banner_type1, img_pos_banner_type2;

    private TextView tv_pos_banner_type3_1, tv_pos_banner_type3_2, tv_pos_banner_type4_1, tv_pos_banner_type4_2;

    private ImageView img_pos_banner_type3, img_pos_banner_type4;

    private TextView tv_pos_banner_type5_1, tv_pos_banner_type5_2, tv_pos_banner_type6_1, tv_pos_banner_type6_2;

    private ImageView img_pos_banner_type5, img_pos_banner_type6;

    private RelativeLayout rel_center_banner1, rel_center_banner2;

    private RelativeLayout rel_pos_banner_type1, rel_pos_banner_type2, rel_pos_banner_type3;

    private LinearLayout ll_pos_banner_type4, ll_pos_banner_type5, ll_pos_banner_type6;


    private BannerResult result;

    @Override
    protected int getResource() {
        return R.layout.fragment_banner;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {


        img_center_banner1 = findViewByIdNoCast(R.id.img_center_banner1);
        img_center_banner2 = findViewByIdNoCast(R.id.img_center_banner2);

        rel_center_banner1 = findViewByIdNoCast(R.id.rel_center_banner1);
        rel_center_banner2 = findViewByIdNoCast(R.id.rel_center_banner2);

        tv_pos_banner_type1_1 = findViewByIdNoCast(R.id.tv_pos_banner_type1_1);
        tv_pos_banner_type1_2 = findViewByIdNoCast(R.id.tv_pos_banner_type1_2);
        tv_pos_banner_type2_1 = findViewByIdNoCast(R.id.tv_pos_banner_type2_1);
        tv_pos_banner_type2_2 = findViewByIdNoCast(R.id.tv_pos_banner_type2_2);

        img_pos_banner_type1 = findViewByIdNoCast(R.id.img_pos_banner_type1);
        img_pos_banner_type2 = findViewByIdNoCast(R.id.img_pos_banner_type2);

        rel_pos_banner_type1 = findViewByIdNoCast(R.id.rel_pos_banner_type1);
        rel_pos_banner_type2 = findViewByIdNoCast(R.id.rel_pos_banner_type2);
        rel_pos_banner_type3 = findViewByIdNoCast(R.id.rel_pos_banner_type3);


        tv_pos_banner_type3_1 = findViewByIdNoCast(R.id.tv_pos_banner_type3_1);
        tv_pos_banner_type3_2 = findViewByIdNoCast(R.id.tv_pos_banner_type3_2);
        tv_pos_banner_type4_1 = findViewByIdNoCast(R.id.tv_pos_banner_type4_1);
        tv_pos_banner_type4_2 = findViewByIdNoCast(R.id.tv_pos_banner_type4_2);

        img_pos_banner_type3 = findViewByIdNoCast(R.id.img_pos_banner_type3);
        img_pos_banner_type4 = findViewByIdNoCast(R.id.img_pos_banner_type4);

        tv_pos_banner_type5_1 = findViewByIdNoCast(R.id.tv_pos_banner_type5_1);
        tv_pos_banner_type5_2 = findViewByIdNoCast(R.id.tv_pos_banner_type5_2);
        tv_pos_banner_type6_1 = findViewByIdNoCast(R.id.tv_pos_banner_type6_1);
        tv_pos_banner_type6_2 = findViewByIdNoCast(R.id.tv_pos_banner_type6_2);

        img_pos_banner_type5 = findViewByIdNoCast(R.id.img_pos_banner_type5);
        img_pos_banner_type6 = findViewByIdNoCast(R.id.img_pos_banner_type6);

        ll_pos_banner_type4 = findViewByIdNoCast(R.id.ll_pos_banner_type4);
        ll_pos_banner_type5 = findViewByIdNoCast(R.id.ll_pos_banner_type5);
        ll_pos_banner_type6 = findViewByIdNoCast(R.id.ll_pos_banner_type6);


        setOnClick(rel_center_banner1,
                rel_center_banner2,
                rel_pos_banner_type1,
                rel_pos_banner_type2,
                rel_pos_banner_type3,
                ll_pos_banner_type4,
                ll_pos_banner_type5,
                ll_pos_banner_type6);

    }

    @Override
    protected void initData() {

        if (result != null) {
            img_center_banner1.setImageURI(Uri.parse(result.result.center_banner.get(0).adImgUrl));
            img_center_banner2.setImageURI(Uri.parse(result.result.center_banner.get(1).adImgUrl));
            tv_pos_banner_type1_1.setText(result.result.position_banner.get(0).bigTitle);
            tv_pos_banner_type1_2.setText(result.result.position_banner.get(0).subTitle);
            img_pos_banner_type1.setImageURI(Uri.parse(result.result.position_banner.get(0).iconUrl));

            tv_pos_banner_type2_1.setText(result.result.position_banner.get(1).bigTitle);
            tv_pos_banner_type2_2.setText(result.result.position_banner.get(1).subTitle);
            img_pos_banner_type2.setImageURI(Uri.parse(result.result.position_banner.get(1).iconUrl));

            tv_pos_banner_type3_1.setText(result.result.position_banner.get(5).bigTitle);
            tv_pos_banner_type3_2.setText(result.result.position_banner.get(5).subTitle);
            img_pos_banner_type3.setImageURI(Uri.parse(result.result.position_banner.get(5).iconUrl));

            tv_pos_banner_type4_1.setText(result.result.position_banner.get(2).bigTitle);
            tv_pos_banner_type4_2.setText(result.result.position_banner.get(2).subTitle);
            img_pos_banner_type4.setImageURI(Uri.parse(result.result.position_banner.get(2).iconUrl));

            tv_pos_banner_type5_1.setText(result.result.position_banner.get(3).bigTitle);
            tv_pos_banner_type5_2.setText(result.result.position_banner.get(3).subTitle);
            img_pos_banner_type5.setImageURI(Uri.parse(result.result.position_banner.get(3).iconUrl));

            tv_pos_banner_type6_1.setText(result.result.position_banner.get(4).bigTitle);
            tv_pos_banner_type6_2.setText(result.result.position_banner.get(4).subTitle);
            img_pos_banner_type6.setImageURI(Uri.parse(result.result.position_banner.get(4).iconUrl));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_center_banner1:
                break;
            case R.id.rel_center_banner2:
                break;
            case R.id.rel_pos_banner_type1:
                break;
            case R.id.rel_pos_banner_type2:
                break;
            case R.id.rel_pos_banner_type3:
                break;
            case R.id.ll_pos_banner_type4:
                break;
            case R.id.ll_pos_banner_type5:
                break;
            case R.id.ll_pos_banner_type6:
                break;
        }
    }

    public void setResult(BannerResult result) {
        this.result = result;
        initData();
    }

}
