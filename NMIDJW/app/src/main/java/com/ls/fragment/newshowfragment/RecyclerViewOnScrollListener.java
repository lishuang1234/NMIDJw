package com.ls.fragment.newshowfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.ls.util.Utils;

/**
 * Created by ls on 15-3-8.
 */
public abstract class RecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {

    private int mOffset;
    private Context mContext;
    private int mToolbarHeight;
    private boolean isVisible = true;

    public RecyclerViewOnScrollListener(Context context) {
        super();
        this.mContext = context;
        mToolbarHeight = Utils.getToolbarHeight(mContext);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {//手指不再滚动
            if (isVisible) {
                if (mOffset > 40) {//开始隐藏
                    setInVisible();
                } else {
                    setVisible();
                }
            } else {
                if (mToolbarHeight - mOffset > 40) {//开始显示
                    setVisible();
                } else {
                    setInVisible();
                }
            }

        }

    }

    private void setVisible() {
        if (mOffset > 0) {
            mOffset = 0;
            onShow();
        }
        isVisible = true;
    }


    private void setInVisible() {
        if (mOffset < mToolbarHeight) {
            mOffset = mToolbarHeight;
            onHide();
        }
        isVisible = false;
    }

    protected abstract void onShow();

    protected abstract void onHide();

    protected abstract void onMove(int offset);


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //向上为正，向下为负,向上要显示，向下隐藏
        clipOffset();
        onMove(mOffset);
        if (dy > 0 && mOffset < mToolbarHeight || dy < 0 && mToolbarHeight > 0) {
            mOffset += dy;
        }

    }

    public void clipOffset() {
        if (mOffset > mToolbarHeight) mOffset = mToolbarHeight;
        if (mOffset < 0) mOffset = 0;
    }
}
