package com.xavier.xaviertranslate.utils;

import com.xavier.xaviertranslate.controller.network.ApiRequestInterface;
import com.xavier.xaviertranslate.model.Language;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zensis on 28/4/16.
 */
public class Constants {
    private static Language[] languageList = {
            new Language("eng", "English"),
            new Language("zh", "中文")
    };

    public static Language[] getLanguageList() {
        return languageList;
    }


    public static String getAPIEndpoint() {
        return "https://glosbe.com/gapi";
    }

    public static Class getAPIInterface() {
        return ApiRequestInterface.class;
    }
}
