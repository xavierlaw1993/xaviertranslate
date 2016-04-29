package com.xavier.xaviertranslate.controller.page;

import com.octo.android.robospice.SpiceManager;
import com.xavier.xaviertranslate.controller.network.ApiRequestService;

import roboguice.activity.RoboActionBarActivity;

/**
 * Created by zensis on 29/4/16.
 */
public class AbsSpiceActivity extends RoboActionBarActivity {
    private static final String TAG = "AbsSpiceActivity";

    private SpiceManager spiceManager = new SpiceManager(ApiRequestService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

}
