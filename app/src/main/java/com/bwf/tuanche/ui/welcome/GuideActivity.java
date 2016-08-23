package com.bwf.tuanche.ui.welcome;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.tuanche.R;
import com.bwf.tuanche.ui.welcome.adapter.GuideAdapter;

/**
 * Created by che on 2016/8/16
 * Description:.
 */
public class GuideActivity extends BaseActivity{
    private ImageView img_guide;
    private ViewPager viewPager_guide;
    private GuideAdapter guideAdapter;
    private Integer[] images = new Integer[]{R.mipmap.guide01,R.mipmap.guide02};

    @Override
    public int getContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        img_guide = findViewByIdNoCast(R.id.img_guide);
        viewPager_guide = findViewByIdNoCast(R.id.viewPager_guide);
    }

    @Override
    public void initData() {
        guideAdapter = new GuideAdapter(getSupportFragmentManager());
        viewPager_guide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCheck(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager_guide.setOffscreenPageLimit(2);
        viewPager_guide.setAdapter(guideAdapter);
        setCheck(0);
    }

    @Override
    public void onClick(View view) {

    }

    public void setCheck(int position){
        img_guide.setImageResource(images[position]);
    }
}
