package com.ls.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ls.fragment.newshowfragment.NewsShowFragment;
import com.ls.fragment.newshowfragment.NewsShowFragmentPagerAdapter;
import com.ls.nmidjw.R;
import com.ls.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ls on 15-3-5.
 */
public class NewsFragment extends Fragment implements ViewPager.OnPageChangeListener,
        View.OnClickListener{

    private Toolbar mToolbar;
    private ActionBarActivity mActivity;
    private ViewPager mViewPager;
    private NewsShowFragmentPagerAdapter pagerAdapter;
    private View rootView;
    private ImageView mIndicatorView;
    private int mScreentWidth;
    private int mIndicatorWidth;
    private float mOffsetWidth;
    private View mInicatorRootView;

    @Override
    public void onAttach(Activity activity) {
        this.mActivity = (ActionBarActivity) activity;
        super.onAttach(activity);
        pagerAdapter = new NewsShowFragmentPagerAdapter(getChildFragmentManager(),
                createFragmentList());
        mScreentWidth = Utils.getScreenWidth(activity);
        mIndicatorWidth = mScreentWidth / 3;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_news, container, false);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar_fragment_news);
        mActivity.setSupportActionBar(mToolbar);


        mViewPager = (ViewPager) rootView.findViewById(R.id.news_fragment_vp);
        mViewPager.setPadding(mViewPager.getPaddingLeft(), Utils.getToolbarHeight(mActivity) +Utils.getTabHeight(mActivity),
                mViewPager.getPaddingRight(), mViewPager.getPaddingBottom());

        mIndicatorView = (ImageView) rootView.findViewById(R.id.fragment_news_tab_indicator);
        mInicatorRootView = rootView.findViewById(R.id.fragment_news_toolbar);

        rootView.findViewById(R.id.fragment_news_job_tx_indicator).setOnClickListener(this);
        rootView.findViewById(R.id.fragment_news_school_tx_indicator).setOnClickListener(this);
        rootView.findViewById(R.id.fragment_news_notify_tx_indicator).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    private List<Fragment> createFragmentList() {
        List<Fragment> list = new ArrayList<>();
        NewsShowFragment showFragment;
        for (int i = 0; i < 3; i++) {
            showFragment = new NewsShowFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", i);
            showFragment.setArguments(bundle);

            list.add(showFragment);
        }
        return list;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        System.out.println("onPageScrolled : position :" + position + " positionOffset ：" +
                positionOffset);
        mOffsetWidth = mIndicatorWidth * positionOffset + position * mIndicatorWidth;
        System.out.println("mOffsetWidth: " + mOffsetWidth);
        mIndicatorView.setTranslationX(mOffsetWidth);

    }

    @Override
    public void onPageSelected(int position) {
        System.out.println("onPageSelected: position" + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_news_job_tx_indicator:
                mViewPager.setCurrentItem(1, true);
                break;
            case R.id.fragment_news_school_tx_indicator:
                mViewPager.setCurrentItem(0, true);
                break;
            case R.id.fragment_news_notify_tx_indicator:
                mViewPager.setCurrentItem(2, true);
                break;

        }
    }


}