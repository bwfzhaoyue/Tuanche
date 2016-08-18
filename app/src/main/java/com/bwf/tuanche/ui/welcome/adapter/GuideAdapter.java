package com.bwf.tuanche.ui.welcome.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwf.tuanche.ui.welcome.fragment.GuideFragment;

/**
 * Created by Asus on 2016/8/17.
 */
public class GuideAdapter extends FragmentPagerAdapter{
    public GuideAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GuideFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
