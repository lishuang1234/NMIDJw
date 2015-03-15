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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ls.adapter.ShowViewPager;
import com.ls.nmidjw.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ls on 15-3-5.
 */
public class FirstPageFragment extends Fragment {

    private ActionBarActivity mActivity;
    private ViewPager mViewPager;
    private List<View> mImages;
    private MyHandler mHandler;
    private int mCurrentPagerItem;


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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_page, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.fragment_first_page_vp_show);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) mActivity.getResources().getDimension(R.dimen.first_page_fragment_iv_show_height));
        setShowViewPagerImage(layoutParams);
        mViewPager.setAdapter(new ShowViewPager(mActivity, mImages));

        System.out.println("FirstPage  onCreateView");
        return view;

    }



    public void setShowViewPagerImage(ViewGroup.LayoutParams showViewPagerImage) {
        mImages = new ArrayList<View>();
        int[] imageViewSrc = new int[]{R.mipmap.first_page_header_background, R.mipmap.header, R.mipmap.ic_launcher};
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(mActivity.getApplicationContext());
            // showViewPagerImage.height = (int) mActivity.getResources().getDimension(R.dimen.first_page_fragment_iv_show_height);
            imageView.setLayoutParams(showViewPagerImage);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageViewSrc[i]);
            mImages.add(imageView);
        }

        mHandler.sendEmptyMessageDelayed(1, 5000);
    }


    private static class MyHandler extends Handler {
        private WeakReference<FirstPageFragment> fragementWeakReference;//存储Fragment虚引用
        private ViewPager viewPager;
        private int currentPagerItem;
        private FirstPageFragment firstPageFragment;

        public MyHandler(FirstPageFragment fragment) {
            this.fragementWeakReference = new WeakReference<FirstPageFragment>(fragment);
            firstPageFragment = fragementWeakReference.get();
        }

        @Override
        public void handleMessage(Message msg) {
            if (firstPageFragment != null) {
                viewPager = firstPageFragment.mViewPager;
                if (msg.what == 1)
                    setCurrentPager();
                firstPageFragment.mHandler.sendEmptyMessageDelayed(1, 3000);
            }
            super.handleMessage(msg);
        }

        private void setCurrentPager() {
            currentPagerItem++;
            System.out.println("currentPagerItem "+currentPagerItem);
            int offset = currentPagerItem % 3;

                viewPager.setCurrentItem(offset, true);

        }
    }


    @Override
    public void onDestroyView() {
        mHandler.removeMessages(1);
        System.out.println("FirstPage  onDestroyView");
        super.onDestroyView();
    }
}
