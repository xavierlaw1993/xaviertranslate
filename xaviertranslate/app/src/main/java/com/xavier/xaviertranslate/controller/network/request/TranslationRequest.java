package com.xavier.xaviertranslate.controller.network.request;

import android.content.Context;

import com.xavier.xaviertranslate.model.response.TranslateResultResponse;

/**
 * Created by zensis on 28/4/16.
 */
public class TranslationRequest extends AbsRequest<TranslateResultResponse> {
    String langFrom, langDest, phrase;

    public TranslationRequest(Context context, String langFrom, String langDest, String phrase) {
        super(context, TranslateResultResponse.class);
        this.langFrom = langFrom;
        this.langDest = langDest;
        this.phrase = phrase;
    }

    @Override
    public TranslateResultResponse loadDataFromNetwork() throws Exception {
        return getService().getTranslateResult(langFrom, langDest, "json", phrase, true);
    }
}
