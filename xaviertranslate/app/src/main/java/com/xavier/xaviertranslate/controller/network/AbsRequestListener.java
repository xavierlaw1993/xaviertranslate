package com.xavier.xaviertranslate.controller.network;

import android.content.Context;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.xavier.xaviertranslate.model.response.AbsResponse;

import roboguice.RoboGuice;

/**
 * Created by zensis on 28/4/16.
 */
public abstract class AbsRequestListener<T extends AbsResponse> implements RequestListener<T> {

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        onFailure(spiceException.getMessage());
    }

    @Override
    public void onRequestSuccess(T t) {
        if (t.isSuccess()) {
            onSuccess(t);
        } else {
            onFailure(t.result);
        }
    }

    public abstract void onSuccess(T t);
    public abstract void onFailure(String msg);
}
