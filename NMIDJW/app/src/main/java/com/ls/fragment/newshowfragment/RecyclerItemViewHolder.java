package com.ls.fragment.newshowfragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ls.nmidjw.R;

/**
 * Created by ls on 15-3-7.
 */
public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView mTextView;

    public RecyclerItemViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.recycler_item_tx);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

}
