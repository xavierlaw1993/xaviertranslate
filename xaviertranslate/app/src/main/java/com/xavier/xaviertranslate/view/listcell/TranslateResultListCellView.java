package com.xavier.xaviertranslate.view.listcell;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xavier.xaviertranslate.R;
import com.xavier.xaviertranslate.model.Meaning;
import com.xavier.xaviertranslate.model.TranslateResult;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 29/4/16.
 */
public class TranslateResultListCellView extends FrameLayout {
    Context context;

    @InjectView(R.id.tv_meaning)
    TextView tv_meaning;

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

    public void bindModel(Meaning meaning) {
        tv_meaning.setText(meaning.text);

    }
}
