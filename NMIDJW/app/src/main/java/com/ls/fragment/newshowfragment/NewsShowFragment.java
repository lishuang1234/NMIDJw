package com.ls.fragment.newshowfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ls.nmidjw.R;
import com.ls.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ls on 15-3-7.
 */
public class NewsShowFragment extends Fragment {
    private View rootView;
    private RecyclerView mRecyclerView;
    private Activity mActivity;
    private int fragmentID;
    private Toolbar mToolbar;
    private OnHideToolbarListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_news_show, null, false);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        //test();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_news_show_rv);

        initRecyclerView();

        return rootView;

    }

    private void test() {
        switch (fragmentID) {
            case 0:
                rootView.setBackgroundColor(getResources().getColor(R.color
                        .color_primary_blue_dark));
                break;
            case 1:
                rootView.setBackgroundColor(getResources().getColor(android.R.color.black));
                break;

            case 2:
                rootView.setBackgroundColor(getResources().getColor(android.R.color
                        .holo_orange_light));
                break;

        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        fragmentID = bundle.getInt("id", 0);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new RecyclerAdapter(createList()));
        mRecyclerView.setOnScrollListener(new RecyclerViewOnScrollListener(mActivity) {
            @Override
            protected void onShow() {
                if (mListener!=null)
                    mListener.onShow();

            }

            @Override
            protected void onHide() {
                if (mListener!=null)
                    mListener.onHide();
            }

            @Override
            protected void onMove(int offset) {
                if (mListener!=null)
                    mListener.onMove(offset);

            }
        });

    }

    private List<String> createList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("NEWSSHOW  " + fragmentID + "   " + i);
        }
        return list;
    }

    /**
     * 处理隐藏显示动作
     */
    public interface OnHideToolbarListener {
        void onShow();

        void onHide();

        void onMove(int offset);
    }

    public void setHideToolbarListener(OnHideToolbarListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }
}
