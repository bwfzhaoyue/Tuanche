package com.bwf.tuanche.ui.welcome.fragment;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.share.SharePrefreceHelper;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.citychoice.CityChoiceActivity;
import com.bwf.tuanche.ui.mainpager.MainPagerActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Asus on 2016/8/17.
 */
public class GuideFragment extends BaseFragment {

    private int position;
    private ImageView img_kaiqi,img_guide;


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
        img_guide = findViewByIdNoCast(R.id.img_guide);
    }

    @Override
    protected void initData() {
        setOnClick(R.id.img_kaiqi);
        switch (position){
            case 0:
                img_guide.setImageResource(R.mipmap.guide01);
                break;
            case 1:
                img_guide.setImageResource(R.mipmap.guide02);
                img_kaiqi.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_kaiqi:
                SharePrefreceHelper.getInstence(getActivity()).setIsFirst(false);
//                img_kaiqi.setBackgroundResource(R.mipmap.start_btn_click);
//                img_kaiqi.setImageResource(R.mipmap.start_btn_click);
                IntentUtils.openActivity(getContext(), CityChoiceActivity.class);
                getActivity().finish();
                break;
        }
    }
}
