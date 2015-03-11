package com.ls.fragment.newshowfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ls on 15-3-7.
 */
public class NewsShowFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public NewsShowFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void removeFragment() {
        if (fragmentList != null) fragmentList.clear();

    }
}
