package com.xavier.xaviertranslate.controller.adapter.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.xavier.xaviertranslate.R;
import com.xavier.xaviertranslate.model.Language;

import java.util.List;

/**
 * Created by zensis on 6/5/16.
 */
public class LanguageAdapter extends ArrayAdapter<Language> {
    List<Language> languages;

    public LanguageAdapter(Context context, List<Language> languages) {
        super(context, 0, languages);
        this.languages = languages;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    public View getCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_language_item, parent, false);
        }

        TextView tv_language = (TextView) convertView.findViewById(R.id.tv_language);
        tv_language.setText(languages.get(position).languageName);

        return convertView;
    }
}
