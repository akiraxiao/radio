package com.gcores.radionews.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.view.base.BaseActivity;
import com.gcores.radionews.ui.wedget.TouchiableWebview;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class DetailActvity extends BaseActivity implements OnRefreshListener {

    private String url;
    private TouchiableWebview mContent;
    private SmartRefreshLayout smartRefreshLayout;
    private boolean loadFrist = true;
    private LinearLayout llBackTop;
    private int commentnum;
    private TextView tvCommentNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        url = getIntent().getStringExtra("url");
        commentnum = getIntent().getIntExtra("commentnum",0);
        mContent = findViewById(R.id.web_content);
        smartRefreshLayout = findViewById(R.id.refreshLayout);
        llBackTop = findViewById(R.id.ll_backtop);
        llBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvCommentNum = findViewById(R.id.tv_comment_num);
        tvCommentNum.setText(commentnum+"");
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setEnableHeaderTranslationContent(true);
        mContent.setWebChromeClient(new WebChromeClient());
        mContent.setWebViewClient(new WebViewClient() {

                                      @Override
                                      public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                          super.onPageStarted(view, url, favicon);
                                      }

                                      @Override
                                      public void onPageFinished(WebView view, String url) {
                                          super.onPageFinished(view, url);
                                          smartRefreshLayout.finishRefresh();
                                      }

                                      @Override
                                      public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                                          super.onReceivedError(view, request, error);
                                          smartRefreshLayout.finishRefresh();
                                      }

                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                          /*if (url == null) return false;

                                          try {
                                              if (url.startsWith("http:") || url.startsWith("https:")) {
                                                  view.loadUrl(url);
                                                  return true;
                                              } else {
                                                  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                                  startActivity(intent);
                                                  return true;
                                              }
                                          } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                                              return false;
                                          }*/
                                          return true;

                                      }
                                  }

        );
        WebSettings webSettings = mContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        smartRefreshLayout.autoRefresh();
//        mContent.loadUrl(url);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        /*if (loadFrist){
            mContent.loadUrl(url);
            loadFrist = !loadFrist;
        }else{*/
           mContent.loadUrl(url);
//        }
    }
}
