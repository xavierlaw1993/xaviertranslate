package com.xavier.xaviertranslate.model.response;

import com.google.gson.annotations.Expose;
import com.xavier.xaviertranslate.model.TranslateResult;

import java.util.List;

/**
 * Created by zensis on 28/4/16.
 */
public class TranslateResultResponse extends AbsResponse {
    @Expose
    public List<TranslateResult> tuc;
}
