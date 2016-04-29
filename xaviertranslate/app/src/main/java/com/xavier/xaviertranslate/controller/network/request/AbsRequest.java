package com.xavier.xaviertranslate.controller.network.request;

import android.content.Context;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.xavier.xaviertranslate.controller.network.ApiRequestInterface;
import com.xavier.xaviertranslate.model.response.AbsResponse;

import roboguice.RoboGuice;

/**
 * Created by zensis on 28/4/16.
 */
public abstract class AbsRequest<T extends AbsResponse> extends RetrofitSpiceRequest<T, ApiRequestInterface> {

    Context context;

    public AbsRequest(Context context, Class<T> clazz) {
        super(clazz, ApiRequestInterface.class);
        RoboGuice.getInjector(context).injectMembersWithoutViews(this);
        this.context = context;
    }
}
