package com.ls.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.ls.adapter.ShowViewPager;
import com.ls.nmidjw.R;
import com.ls.util.FixedSpeedScroller;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ls on 15-3-5.
 */
public class FirstPageFragment extends Fragment implements View.OnTouchListener {

    private ActionBarActivity mActivity;
    private ViewPager mViewPager;
    private List<View> mImages;
    private MyHandler mHandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        this.mActivity = (ActionBarActivity) activity;
        super.onAttach(activity);
        mHandler = new MyHandler(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_page, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.fragment_first_page_vp_show);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, (int) mActivity.getResources().getDimension(R.dimen
                .first_page_fragment_iv_show_height));
        setShowViewPagerImage(layoutParams);
        mViewPager.setAdapter(new ShowViewPager(mActivity, mImages));
        mViewPager.setOnTouchListener(this);
        changeViewPagerScroller();
        return view;
    }

    /**
     * 反射修改ViewPager滑动速度
     */
    private void changeViewPagerScroller() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mViewPager.getContext());
            scroller.setmDuration(2000);
            field.set(mViewPager, scroller);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setShowViewPagerImage(ViewGroup.LayoutParams showViewPagerImage) {
        mImages = new ArrayList<>();
        int[] imageViewSrc = new int[]{R.mipmap.first_page_header_background, R.mipmap.header,
                R.mipmap.ic_launcher};
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(mActivity.getApplicationContext());
            imageView.setLayoutParams(showViewPagerImage);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageViewSrc[i]);
            mImages.add(imageView);
        }

        mHandler.sendEmptyMessageDelayed(1, 4000);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;//禁止ViewPager滑动
    }


    private static class MyHandler extends Handler {
        private WeakReference<FirstPageFragment> fragmentWeakReference;//存储Fragment虚引用
        private ViewPager viewPager;
        private int currentPagerItem;
        private FirstPageFragment firstPageFragment;
        private boolean exchange;

        public MyHandler(FirstPageFragment fragment) {
            this.fragmentWeakReference = new WeakReference<>(fragment);
            firstPageFragment = fragmentWeakReference.get();
        }

        @Override
        public void handleMessage(Message msg) {
            if (firstPageFragment != null) {
                viewPager = firstPageFragment.mViewPager;
                if (msg.what == 1) setCurrentPager();
                firstPageFragment.mHandler.sendEmptyMessageDelayed(1, 4000);
            }
            super.handleMessage(msg);
        }

        private void setCurrentPager() {
            if (exchange) {
                currentPagerItem--;
                if (currentPagerItem == 0) exchange = false;
            } else {
                currentPagerItem++;
                if (currentPagerItem == 2) exchange = true;
            }
            int offset = currentPagerItem % 3;
            viewPager.setCurrentItem(offset, true);
        }
    }


    @Override
    public void onDestroyView() {
        mHandler.removeMessages(1);//取消发送消息
        super.onDestroyView();
    }
}
