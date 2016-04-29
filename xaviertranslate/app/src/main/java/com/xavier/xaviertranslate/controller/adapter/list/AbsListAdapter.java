package com.xavier.xaviertranslate.controller.adapter.list;

import android.support.v7.widget.RecyclerView;

/**
 * Created by zensis on 29/4/16.
 */
public abstract class AbsListAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener<I> {
        void onItemClick(I item);
    }
}
