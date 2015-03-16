package com.ls.fragment.newshowfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
public class NewsShowFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View rootView;
    private RecyclerView mRecyclerView;
    private Activity mActivity;
    private int fragmentID;
    private Toolbar mToolbar;
    private OnHideToolbarListener mListener;
    private SwipeRefreshLayout refreshLayout;

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


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_news_show_rv);
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.fragment_news_show_sl);

        initRecyclerView();
        initRefreshLayout();
        return rootView;

    }

    private void initRefreshLayout() {
        refreshLayout.setColorSchemeResources(R.color.orange, R.color.blue, R.color.green);
        refreshLayout.setOnRefreshListener(this);
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
                if (mListener != null) mListener.onShow();

            }

            @Override
            protected void onHide() {
                if (mListener != null) mListener.onHide();
            }

            @Override
            protected void onMove(int offset) {
                if (mListener != null) mListener.onMove(offset);

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
     * 下拉刷新
     */
    @Override
    public void onRefresh() {

        //refreshLayout.setRefreshing(false);

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
