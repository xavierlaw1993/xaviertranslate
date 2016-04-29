package com.xavier.xaviertranslate.model.response;

import com.google.gson.annotations.Expose;

/**
 * Created by zensis on 28/4/16.
 */
public class AbsResponse {
    @Expose
    public String result;

    public boolean isSuccess(){
        return result.equals("ok");
    }
}
