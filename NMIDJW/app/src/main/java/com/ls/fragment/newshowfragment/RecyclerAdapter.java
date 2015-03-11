package com.ls.fragment.newshowfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ls.nmidjw.R;

import java.util.List;

/**
 * Created by ls on 15-3-7.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {
    private List<String> list;

  public RecyclerAdapter(List<String> list){
      this.list =list;

  }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_news_recycler_item, parent, false);
        return new RecyclerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerItemViewHolder itemViewHolder = (RecyclerItemViewHolder) holder;
        itemViewHolder.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
