package com.xavier.xaviertranslate.view.listcell;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.text.Html;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xavier.xaviertranslate.R;
import com.xavier.xaviertranslate.model.Meaning;
import com.xavier.xaviertranslate.utils.Utils;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 6/5/16.
 */
public class TranslateResultMeaningListCellView extends LinearLayout {
    Context context;

    @InjectView(R.id.tv_meaning)
    TextView tv_meaning;

    public TranslateResultMeaningListCellView(Context context) {
        super(context);
        init(context);
    }

    public TranslateResultMeaningListCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TranslateResultMeaningListCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TranslateResultMeaningListCellView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_translate_result_meaning_list_cell, this);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);
    }

    public void bindModel(Meaning meaning, boolean isPhrase) {
//        if (!Utils.langDest.equals(meaning.language))
//            tv_meaning.setPaintFlags(tv_meaning.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if (isPhrase) {
            tv_meaning.setText(Html.fromHtml(meaning.text));
            tv_meaning.setTextColor(getResources().getColor(R.color.colorAccent));
            tv_meaning.setPaintFlags(tv_meaning.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);

        } else {
            tv_meaning.setText(Html.fromHtml(meaning.text));
            tv_meaning.setPaintFlags(tv_meaning.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
    }
}
