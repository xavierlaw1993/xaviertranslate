package com.xavier.xaviertranslate.controller.adapter.list;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xavier.xaviertranslate.model.Meaning;
import com.xavier.xaviertranslate.view.listcell.TranslateResultMeaningListCellView;

import java.util.List;

/**
 * Created by zensis on 6/5/16.
 */
public class TranslateResultMeaningListAdapter extends AbsListAdapter<TranslateResultMeaningListAdapter.TranslateResultMeaningViewHolder> {
    Meaning phrase;
    List<Meaning> meanings;

    public TranslateResultMeaningListAdapter(Meaning phrase, List<Meaning> meanings) {
        this.phrase = phrase;
        this.meanings = meanings;
    }

    @Override
    public TranslateResultMeaningViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TranslateResultMeaningListCellView view = new TranslateResultMeaningListCellView(parent.getContext());
        TranslateResultMeaningViewHolder holder = new TranslateResultMeaningViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TranslateResultMeaningViewHolder holder, int position) {
        if (phrase != null) {
            if (position == 0)
                holder.view.bindModel(phrase, true);
            else
                holder.view.bindModel(meanings.get(position - 1), false);
        } else
            holder.view.bindModel(meanings.get(position), false);
    }

    @Override
    public int getItemCount() {
        if (meanings != null)
            return phrase != null ? meanings.size() + 1 : meanings.size();
        else
            return phrase != null ? 1 : 0;
    }

    public class TranslateResultMeaningViewHolder extends RecyclerView.ViewHolder {

        TranslateResultMeaningListCellView view;

        public TranslateResultMeaningViewHolder(TranslateResultMeaningListCellView view) {
            super(view);
            this.view = view;
        }
    }
}
