package com.gcores.radionews.ui;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.view.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class UrlActicity extends BaseActivity implements OnRefreshListener {

    private String url;
    private WebView mContent;
    private SmartRefreshLayout smartRefreshLayout;

    private LinearLayout llBackTop;

    private TextView tvCommentNum;

    ImageView ivBottom;
    TextView tvTitleCenter;
    String title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        mContent = findViewById(R.id.web_content);
        tvTitleCenter = findViewById(R.id.txt_title_center);
        tvTitleCenter.setText(title);
        smartRefreshLayout = findViewById(R.id.refreshLayout);
        llBackTop = findViewById(R.id.ll_backtop);
        llBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvCommentNum = findViewById(R.id.tv_comment_num);
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setEnableHeaderTranslationContent(true);
        mContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {

                    mContent.getSettings().setBlockNetworkImage(false);
//                    mContent.getSettings().setLoadsImagesAutomatically(true);
                }
            }
        });
        mContent.setWebViewClient(new WebViewClient() {

                                      @Override
                                      public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                          super.onPageStarted(view, url, favicon);
                                          smartRefreshLayout.finishRefresh();
                                      }

                                      @Override
                                      public void onPageFinished(WebView view, String url) {
//                                          tvTitleCenter.setText(view.getTitle());
                                          super.onPageFinished(view, url);

                                      }

                                      @Override
                                      public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                                          super.onReceivedError(view, request, error);
//                                          smartRefreshLayout.finishRefresh();
                                      }


                                      @TargetApi(Build.VERSION_CODES.N)
                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                          String uri =  request.getUrl().toString();
                                          return false;
                                      }

                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, String requstUrl) {
//
                                          return true;
                                      }
                                  }

        );
        WebSettings webSettings = mContent.getSettings();
        // 启动应用缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式
        //  页面加载好以后，再放开图片
        webSettings.setBlockNetworkImage(false);
        // 排版适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(true);
        ivBottom = findViewById(R.id.iv_player_bottom);
        smartRefreshLayout.autoRefresh();
//        mContent.loadUrl(url);
    }

    @Override
    protected void onResume() {
        super.onResume();


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

