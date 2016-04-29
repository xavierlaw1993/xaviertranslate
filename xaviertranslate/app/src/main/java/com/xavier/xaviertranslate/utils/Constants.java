package com.xavier.xaviertranslate.utils;

import com.xavier.xaviertranslate.controller.network.ApiRequestInterface;

/**
 * Created by zensis on 28/4/16.
 */
public class Constants {
    public static String getAPIEndpoint() {
        return "https://glosbe.com/gapi";
    }

    public static Class getAPIInterface() {
        return ApiRequestInterface.class;
    }
}
