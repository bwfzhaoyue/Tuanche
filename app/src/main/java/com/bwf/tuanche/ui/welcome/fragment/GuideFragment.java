package com.bwf.tuanche.ui.welcome.fragment;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.share.SharePrefreceHelper;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.mainpager.MainPagerActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Asus on 2016/8/17.
 */
public class GuideFragment extends BaseFragment {

    private int position;
    private ImageView img_kaiqi;

    public static GuideFragment newInstance(int position){
        GuideFragment guideFragment = new GuideFragment();
        guideFragment.position = position;
        return guideFragment;
    }
    @Override
    protected int getResource() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        img_kaiqi = findViewByIdNoCast(R.id.img_kaiqi);
    }

    @Override
    protected void initData() {
        setOnClick(R.id.img_kaiqi);
        switch (position){
            case 1:
                img_kaiqi.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_kaiqi:
                SharePrefreceHelper.getInstence(getActivity()).setIsFirst(false);
                img_kaiqi.setBackgroundResource(R.mipmap.start_btn_click);
                img_kaiqi.setImageResource(R.mipmap.start_btn_click);
                IntentUtils.openActivity(getContext(), MainPagerActivity.class);
                getActivity().finish();
                break;
        }
    }
}
