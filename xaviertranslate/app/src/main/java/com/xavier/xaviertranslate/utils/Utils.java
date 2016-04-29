package com.xavier.xaviertranslate.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

/**
 * Created by zensis on 29/4/16.
 */
public class Utils {
    private static String TAG = "Utils";

    public static int screenWidth = 0;
    public static int screenHeight = 0;

    public static void getScreenSize(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        Log.v(TAG, "Screen W " + screenWidth + " Screen H " + screenHeight);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int densityDpi = (int) (metrics.density * 160f);
        Log.v(TAG, "dpi " + densityDpi);
    }

    public static boolean canDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else {
            return Settings.canDrawOverlays(context);
        }
    }
}
