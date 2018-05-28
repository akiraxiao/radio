package com.gcores.radionews.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.view.base.BaseActivity;

public class DetailActvity extends BaseActivity {

    private String url;
    private WebView mContent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        url = getIntent().getStringExtra("url");
        mContent = findViewById(R.id.web_content);
        WebSettings webSettings =  mContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mContent.loadUrl(url);
    }
}
