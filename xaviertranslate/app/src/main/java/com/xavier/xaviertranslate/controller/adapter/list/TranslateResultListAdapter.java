package com.xavier.xaviertranslate.controller.adapter.list;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xavier.xaviertranslate.model.TranslateResult;
import com.xavier.xaviertranslate.view.listcell.TranslateResultListCellView;

import java.util.List;

/**
 * Created by zensis on 29/4/16.
 */
public class TranslateResultListAdapter extends AbsListAdapter<TranslateResultListAdapter.TranslateResultViewHolder> {

    List<TranslateResult> translateResults;

    public TranslateResultListAdapter(List<TranslateResult> translateResults) {
        this.translateResults = translateResults;
    }

    @Override
    public TranslateResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TranslateResultListCellView view = new TranslateResultListCellView(parent.getContext());
        TranslateResultViewHolder holder = new TranslateResultViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TranslateResultViewHolder holder, int position) {
        holder.view.bindModel(translateResults.get(0).meanings.get(position));
    }

    @Override
    public int getItemCount() {
        return translateResults.size();
    }

    public class TranslateResultViewHolder extends RecyclerView.ViewHolder {

        TranslateResultListCellView view;

        public TranslateResultViewHolder(TranslateResultListCellView view) {
            super(view);
            this.view = view;
        }
    }
}
