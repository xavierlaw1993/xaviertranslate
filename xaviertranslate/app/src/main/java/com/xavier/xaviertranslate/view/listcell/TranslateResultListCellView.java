package com.xavier.xaviertranslate.view.listcell;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xavier.xaviertranslate.R;
import com.xavier.xaviertranslate.controller.adapter.list.TranslateResultMeaningListAdapter;
import com.xavier.xaviertranslate.model.TranslateResult;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 29/4/16.
 */
public class TranslateResultListCellView extends LinearLayout {
    Context context;

    @InjectView(R.id.rv_translateResultMeaningList)
    RecyclerView rv_translateResultMeaningList;

    public TranslateResultListCellView(Context context) {
        super(context);
        init(context);
    }

    public TranslateResultListCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TranslateResultListCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TranslateResultListCellView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_translate_result_list_cell, this);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);
    }

    public void bindModel(TranslateResult translateResult) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        TranslateResultMeaningListAdapter adapter =
                new TranslateResultMeaningListAdapter(translateResult.phrase, translateResult.meanings);
        rv_translateResultMeaningList.setLayoutManager(layoutManager);
        rv_translateResultMeaningList.setAdapter(adapter);
    }
}
