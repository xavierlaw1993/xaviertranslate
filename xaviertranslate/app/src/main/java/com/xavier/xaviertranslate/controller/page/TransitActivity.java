package com.xavier.xaviertranslate.controller.page;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xavier.xaviertranslate.R;
import com.xavier.xaviertranslate.controller.ChatHeadLikeService;
import com.xavier.xaviertranslate.utils.Utils;

public class TransitActivity extends Activity {
    public static int OVERLAY_PERMISSION_REQ_CODE_CHATHEAD = 1234;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transit);
        Utils.getScreenSize(this);

        if (Utils.canDrawOverlays(TransitActivity.this))
            startChatHead();
        else {
            requestPermission(OVERLAY_PERMISSION_REQ_CODE_CHATHEAD);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OVERLAY_PERMISSION_REQ_CODE_CHATHEAD) {
            if (!Utils.canDrawOverlays(TransitActivity.this)) {
                needPermissionDialog(requestCode);
            } else {
                startChatHead();
            }
        }
    }

    private void startChatHead() {
        startService(new Intent(TransitActivity.this, ChatHeadLikeService.class));
    }

    private void requestPermission(int requestCode) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, requestCode);
    }

    private void needPermissionDialog(final int requestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TransitActivity.this);
        builder.setMessage("You need to allow permission");
        builder.setPositiveButton("OK",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission(requestCode);
                    }
                });
        builder.setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

}
