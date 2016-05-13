package com.xavier.xaviertranslate.model;

import com.google.gson.annotations.Expose;

/**
 * Created by zensis on 9/5/16.
 */
public class Language {
    public Language(String languageId, String languageName) {
        this.languageId = languageId;
        this.languageName = languageName;
    }

    @Expose
    public String languageId;

    @Expose
    public String languageName;

}
