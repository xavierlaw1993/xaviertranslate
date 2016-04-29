package com.xavier.xaviertranslate.controller.page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xavier.xaviertranslate.R;
import com.xavier.xaviertranslate.controller.adapter.list.TranslateResultListAdapter;
import com.xavier.xaviertranslate.controller.network.AbsRequestListener;
import com.xavier.xaviertranslate.controller.network.request.TranslationRequest;
import com.xavier.xaviertranslate.model.TranslateResult;
import com.xavier.xaviertranslate.model.response.TranslateResultResponse;
import com.xavier.xaviertranslate.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AbsSpiceActivity {
    public static boolean active = false;
    public static Activity mainActivity;

    @InjectView(R.id.ll_content)
    LinearLayout ll_content;

    @InjectView(R.id.rv_translateResultList)
    RecyclerView rv_translateResultList;

    @InjectView(R.id.et_enterTranslatePhrase)
    EditText et_enterTranslatePhrase;

    List<TranslateResult> translateResultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = MainActivity.this;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_content.getLayoutParams();
        params.width = Utils.screenWidth * 9 / 10;
        params.height = Utils.screenHeight / 2;
        ll_content.setLayoutParams(params);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        TranslateResultListAdapter adapter = new TranslateResultListAdapter(translateResultList);
        rv_translateResultList.setLayoutManager(layoutManager);
        rv_translateResultList.setAdapter(adapter);

        et_enterTranslatePhrase.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    requestTranslationAPI();
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        active = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        active = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
    }

    public void onTranslateClick(View v) {
        requestTranslationAPI();
    }

    private void requestTranslationAPI() {
        getSpiceManager().execute(
                new TranslationRequest(this, "eng", "eng",
                        et_enterTranslatePhrase.getEditableText().toString()),
                new TranslationRequestListener());
    }

    private class TranslationRequestListener extends AbsRequestListener<TranslateResultResponse> {

        @Override
        public void onSuccess(TranslateResultResponse translateResultResponse) {
            translateResultList.addAll(translateResultResponse.tuc);
            rv_translateResultList.getAdapter().notifyDataSetChanged();
        }

        @Override
        public void onFailure(String msg) {

        }
    }
}
