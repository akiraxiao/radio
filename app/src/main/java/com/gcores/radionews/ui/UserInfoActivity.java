package com.gcores.radionews.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.view.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class UserInfoActivity extends BaseActivity implements OnRefreshListener {

    private String url;
    private WebView mContent;
    private SmartRefreshLayout smartRefreshLayout;
    private LinearLayout llBackTop;
    private CoordinatorLayout parent;
//    private int commentnum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        url = getIntent().getStringExtra("url");
        parent = findViewById(R.id.coordinator);
        mContent = findViewById(R.id.web_content);
        smartRefreshLayout = findViewById(R.id.refreshLayout);
        llBackTop = findViewById(R.id.ll_backtop);
        llBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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


                                      @TargetApi(Build.VERSION_CODES.N)
                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                          String requstUrl = request.getUrl().toString();
                                          if (requstUrl.equals("ios://pageLoadComplete")){
                                              //加载完毕
                                              return true;
                                          }

                                          if (requstUrl.startsWith("https://miiverse.nintendo.net")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","miiverse");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;

                                          }
                                         if (requstUrl.startsWith("https://account.xbox.com")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","XBOX");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;
                                         }

                                          if (requstUrl.startsWith("http://psnprofiles.com")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","PSN");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("https://alioss.g-cores.com/assets/user_social")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","QQ");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("http://steamcommunity.com")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","STEAM");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("http://weibo.com")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","个人微博");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("ios://showOriginal")){
                                              //文章
                                              String[] arr = requstUrl.split("/");
                                              //其他文章
                                              String articleid = arr[arr.length - 1];
                                              String url = "https://www.g-cores.com/api/originals/" + articleid + "/html_content?auth_exclusive=" + Constant.AUTH_EXCLUSIVE + "&auth_token=" + Constant.AUTH_TOKEN;
                                              Intent intent = new Intent(UserInfoActivity.this, DetailActvity.class);
                                              intent.putExtra("url", url);
                                              intent.putExtra("orginid",Integer.parseInt(articleid));
                                              startActivity(intent);
                                              return true;
                                          }
                                          if (requstUrl.startsWith("ios://showSocial")){
                                              Snackbar.make(parent, "QQ", Snackbar.LENGTH_SHORT).show();
                                              return true;
                                          }

                                          return false;
                                      }

                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, String requstUrl) {
                                          if (requstUrl.equals("ios://pageLoadComplete")){
                                              //加载完毕
                                              return true;
                                          }

                                          if (requstUrl.startsWith("https://miiverse.nintendo.net")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","miiverse");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;

                                          }
                                          if (requstUrl.startsWith("https://account.xbox.com")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","XBOX");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("http://psnprofiles.com")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","PSN");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("https://alioss.g-cores.com/assets/user_social")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","QQ");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("http://steamcommunity.com")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","STEAM");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("http://weibo.com")){
                                              Intent intent = new Intent(UserInfoActivity.this,UrlActicity.class);
                                              intent.putExtra("title","个人微博");
                                              intent.putExtra("url",requstUrl);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("ios://showOriginal")){
                                              //文章
                                              String[] arr = requstUrl.split("/");
                                              //其他文章
                                              String articleid = arr[arr.length - 1];
                                              String url = "https://www.g-cores.com/api/originals/" + articleid + "/html_content?auth_exclusive=" + Constant.AUTH_EXCLUSIVE + "&auth_token=" + Constant.AUTH_TOKEN;
                                              Intent intent = new Intent(UserInfoActivity.this, DetailActvity.class);
                                              intent.putExtra("url", url);
                                              intent.putExtra("orginid",Integer.parseInt(articleid));
                                              startActivity(intent);
                                              return true;
                                          }


                                          return false;
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





        smartRefreshLayout.autoRefresh();
//        mContent.loadUrl(url);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        mContent.loadUrl(url);
//        }
    }
}
