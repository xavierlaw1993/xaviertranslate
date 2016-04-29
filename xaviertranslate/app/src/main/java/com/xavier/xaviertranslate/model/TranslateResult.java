package com.xavier.xaviertranslate.model;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by zensis on 28/4/16.
 */
public class TranslateResult {
    @Expose
    public List<Meaning> meanings;

    @Expose
    public Meaning phrase;
}
