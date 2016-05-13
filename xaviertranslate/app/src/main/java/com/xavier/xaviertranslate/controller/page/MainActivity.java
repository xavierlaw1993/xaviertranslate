package com.xavier.xaviertranslate.controller.page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.xavier.xaviertranslate.R;
import com.xavier.xaviertranslate.controller.adapter.list.TranslateResultListAdapter;
import com.xavier.xaviertranslate.controller.adapter.spinner.LanguageAdapter;
import com.xavier.xaviertranslate.controller.network.AbsRequestListener;
import com.xavier.xaviertranslate.controller.network.request.TranslationRequest;
import com.xavier.xaviertranslate.model.TranslateResult;
import com.xavier.xaviertranslate.model.response.TranslateResultResponse;
import com.xavier.xaviertranslate.utils.Constants;
import com.xavier.xaviertranslate.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
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

    @InjectView(R.id.tv_empty_result)
    TextView tv_empty_result;

    @InjectView(R.id.rl_progress_bar)
    RelativeLayout rl_progress_bar;

    @InjectView(R.id.sp_translate_from)
    Spinner sp_translate_from;

    @InjectView(R.id.sp_translate_dest)
    Spinner sp_translate_dest;

    List<TranslateResult> translateResultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = MainActivity.this;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_content.getLayoutParams();
        params.width = Utils.screenWidth * 9 / 10;
        params.height = (int) (Utils.screenHeight / 2);
        ll_content.setLayoutParams(params);

        final LanguageAdapter translateLangFromAdapter = new LanguageAdapter(this, Arrays.asList(Constants.getLanguageList()));
        final LanguageAdapter translateLangDestAdapter = new LanguageAdapter(this, Arrays.asList(Constants.getLanguageList()));

        sp_translate_from.setAdapter(translateLangFromAdapter);
        sp_translate_dest.setAdapter(translateLangDestAdapter);

        sp_translate_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Utils.langFrom = translateLangFromAdapter.getItem(position).languageId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_translate_dest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Utils.langDest = translateLangDestAdapter.getItem(position).languageId;
                initAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rl_progress_bar.setVisibility(View.GONE);

        tv_empty_result.setVisibility(View.VISIBLE);
        tv_empty_result.setText(getString(R.string.main__init_page_hint));

        initAdapter();

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
        Utils.hideKeyboard(this);
        requestTranslationAPI();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        TranslateResultListAdapter adapter = new TranslateResultListAdapter(translateResultList);
        rv_translateResultList.setLayoutManager(layoutManager);
        rv_translateResultList.setAdapter(adapter);
    }

    private void requestTranslationAPI() {
        rl_progress_bar.setVisibility(View.VISIBLE);
        translateResultList.clear();
        rv_translateResultList.setVisibility(View.GONE);
        getSpiceManager().execute(
                new TranslationRequest(this, Utils.langFrom, Utils.langDest,
                        et_enterTranslatePhrase.getEditableText().toString()),
                new TranslationRequestListener());
        tv_empty_result.setVisibility(View.GONE);
    }

    private class TranslationRequestListener extends AbsRequestListener<TranslateResultResponse> {

        @Override
        public void onSuccess(TranslateResultResponse translateResultResponse) {
            rl_progress_bar.setVisibility(View.GONE);
            if (translateResultResponse.tuc == null || translateResultResponse.tuc.size() == 0) {
                tv_empty_result.setText(getString(R.string.main__empty_result_hint));
                tv_empty_result.setVisibility(View.VISIBLE);
                rv_translateResultList.setVisibility(View.GONE);
            } else {
                translateResultList.addAll(translateResultResponse.tuc);
                tv_empty_result.setVisibility(View.GONE);
                rv_translateResultList.setVisibility(View.VISIBLE);
                rv_translateResultList.getAdapter().notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(String msg) {
            rl_progress_bar.setVisibility(View.GONE);
            Utils.showSnackBarMsg(findViewById(android.R.id.content), msg);
        }
    }
}
