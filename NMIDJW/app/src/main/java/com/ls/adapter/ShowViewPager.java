package com.ls.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LS on 2015/3/14 0014.
 */
public class ShowViewPager extends PagerAdapter {
    private Activity mActivity;
    private List<View> mImages;

    public ShowViewPager(Activity activity, List<View> images) {

        this.mActivity = activity;
        this.mImages = images;

    }

    @Override
    public int getCount() {
        return mImages.size();

    }

    @Override
    public boolean isViewFromObject(View view, Object o) {

        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImages.get(position));
        return mImages.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImages.get(position));

    }
}
