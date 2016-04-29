package com.xavier.xaviertranslate.controller.network;

import com.xavier.xaviertranslate.model.response.TranslateResultResponse;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by zensis on 28/4/16.
 */
public interface ApiRequestInterface {
    @GET("/translate")
    TranslateResultResponse getTranslateResult(@Query("from") String langFrom,
                                               @Query("dest") String langDest,
                                               @Query("format") String resultFormat,
                                               @Query("phrase") String phraseToTranslate,
                                               @Query("pretty") boolean isPretty);
}
