package com.ls.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

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
        ImageView view = (ImageView) mImages.get(position);
        ViewParent parent = view.getParent();
        if (parent != null) {//删除Item
            ViewGroup group = (ViewGroup) parent;
            group.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
