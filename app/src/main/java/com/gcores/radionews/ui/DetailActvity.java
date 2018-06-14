package com.gcores.radionews.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.api.NewsApi;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
import com.gcores.radionews.ui.model.news.Oringin;
import com.gcores.radionews.ui.resmoel.OringinDetailRes;
import com.gcores.radionews.ui.view.base.BaseActivity;
import com.gcores.radionews.ui.wedget.TouchiableWebview;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActvity extends BaseActivity implements OnRefreshListener {

    private String url;
    private TouchiableWebview mContent;
    private SmartRefreshLayout smartRefreshLayout;
    private boolean loadFrist = true;
    private LinearLayout llBackTop;
    private int commentnum;
    private TextView tvCommentNum;
    private String currentUserid;

    private int volumeid;


    private boolean isRadio;
    private String imageUrl;
    private String radiotitle;

    private int orginid;
    private NewsApi newsApi;
    private Oringin oringin;
    private String orincommentNum;
    ImageView ivBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        url = getIntent().getStringExtra("url");

        commentnum = getIntent().getIntExtra("commentnum", 0);
        currentUserid = getIntent().getIntExtra("userid", 0) + "";
        isRadio = getIntent().getBooleanExtra("isRadio", false);
        orginid = getIntent().getIntExtra("orginid", 0);
        if (isRadio) {
            volumeid = getIntent().getIntExtra("volumeid", 0);
            radiotitle = getIntent().getStringExtra("radiotitle");
            imageUrl = getIntent().getStringExtra("imageurl");
        }

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
        tvCommentNum.setText(commentnum + "");
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
                                          String requstUrl = request.getUrl().toString();
                                          if (requstUrl.equals("ios://pageLoadComplete")) {
                                              //加载完毕
                                              return true;
                                          }
                                          if (requstUrl.equals("ios://playedVideo")) {
                                              Log.e("111", "playvideo");
                                              //播放视频
                                              return true;
                                          }

                                          //播放音频
                                          if (requstUrl.equals("ios://playAudio")) {
                                              Intent intent = new Intent(DetailActvity.this, AudioDetailActvity.class);
                                              intent.putExtra("volumeid", volumeid);
                                              intent.putExtra("radiotitle", radiotitle);
                                              intent.putExtra("imageurl", imageUrl);
                                              intent.putExtra("audiourl", oringin.getMedia().getMp3().get(0));
                                              if (commentnum == 0) {
                                                  intent.putExtra("commentnum", orincommentNum);
                                              } else {
                                                  intent.putExtra("commentnum", commentnum + "");
                                              }
                                              startActivity(intent);
                                              return true;
                                          }
                                          if (requstUrl.startsWith("ios://showAuthor")) {
                                              //用户中心
                                              /*String[] arr =  requstUrl.split("/");
                                              String userid;
                                              if (arr[arr.length-1].length()==0){
                                                  //当前用户
                                                  userid = currentUserid;
                                              }else{
                                                  //其他用户
                                                  userid = arr[arr.length-1];
                                              }*/
                                              String url = "https://www.g-cores.com/api/users/" + currentUserid + "/show_page?auth_exclusive=" + Constant.AUTH_EXCLUSIVE;
                                              Intent intent = new Intent(DetailActvity.this, UserInfoActivity.class);
                                              intent.putExtra("url", url);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("ios://showUser")) {
                                              //用户中心
                                              String[] arr = requstUrl.split("/");
                                              String userid = arr[arr.length - 1];
                                              String url = "https://www.g-cores.com/api/users/" + userid + "/show_page?auth_exclusive=" + Constant.AUTH_EXCLUSIVE;
                                              Intent intent = new Intent(DetailActvity.this, UserInfoActivity.class);
                                              intent.putExtra("url", url);
                                              startActivity(intent);
                                              return true;
                                          }
                                          if (requstUrl.startsWith("ios://showOriginal")) {
                                              //文章
                                              String[] arr = requstUrl.split("/");
                                              //其他文章
                                              String articleid = arr[arr.length - 1];
                                              String url = "https://www.g-cores.com/api/originals/" + articleid + "/html_content?auth_exclusive=" + Constant.AUTH_EXCLUSIVE + "&auth_token=" + Constant.AUTH_TOKEN;
                                              Intent intent = new Intent(DetailActvity.this, DetailActvity.class);
                                              intent.putExtra("url", url);
                                              intent.putExtra("orginid",Integer.parseInt(articleid));
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("ios://showCategory")) {
                                              //分类
                                              String[] arr = requstUrl.split("/");
                                              //其他分类
                                              String cateid = arr[arr.length - 1];
                                              Intent intent = new Intent(DetailActvity.this, CateDetailActvity.class);
                                              intent.putExtra("cateid", Integer.parseInt(cateid));
                                              startActivity(intent);
                                              return true;
                                          }
                                          return false;
                                      }

                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, String requstUrl) {
//                                          String requstUrl = request.getUrl().toString();
                                          if (requstUrl.equals("ios://pageLoadComplete")) {
                                              //加载完毕
                                              return true;
                                          }
                                          if (requstUrl.equals("ios://playedVideo")) {
                                              Log.e("111", "playvideo");
                                              //播放视频
                                              return true;
                                          }

                                          //播放音频
                                          if (requstUrl.equals("ios://playAudio")) {
                                              Intent intent = new Intent(DetailActvity.this, AudioDetailActvity.class);
                                              intent.putExtra("volumeid", volumeid);
                                              intent.putExtra("radiotitle", radiotitle);
                                              intent.putExtra("imageurl", imageUrl);
                                              intent.putExtra("audiourl", oringin.getMedia().getMp3().get(0));
                                              if (commentnum == 0) {
                                                  intent.putExtra("commentnum", orincommentNum);
                                              } else {
                                                  intent.putExtra("commentnum", commentnum + "");
                                              }
                                              startActivity(intent);
                                              return true;
                                          }
                                          if (requstUrl.startsWith("ios://showAuthor")) {
                                              //用户中心
                                              /*String[] arr =  requstUrl.split("/");
                                              String userid;
                                              if (arr[arr.length-1].length()==0){
                                                  //当前用户
                                                  userid = currentUserid;
                                              }else{
                                                  //其他用户
                                                  userid = arr[arr.length-1];
                                              }*/
                                              String url = "https://www.g-cores.com/api/users/" + currentUserid + "/show_page?auth_exclusive=" + Constant.AUTH_EXCLUSIVE;
                                              Intent intent = new Intent(DetailActvity.this, UserInfoActivity.class);
                                              intent.putExtra("url", url);
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("ios://showUser")) {
                                              //用户中心
                                              String[] arr = requstUrl.split("/");
                                              String userid = arr[arr.length - 1];
                                              String url = "https://www.g-cores.com/api/users/" + userid + "/show_page?auth_exclusive=" + Constant.AUTH_EXCLUSIVE;
                                              Intent intent = new Intent(DetailActvity.this, UserInfoActivity.class);
                                              intent.putExtra("url", url);
                                              startActivity(intent);
                                              return true;
                                          }
                                          if (requstUrl.startsWith("ios://showOriginal")) {
                                              //文章
                                              String[] arr = requstUrl.split("/");
                                              //其他文章
                                              String articleid = arr[arr.length - 1];
                                              String url = "https://www.g-cores.com/api/originals/" + articleid + "/html_content?auth_exclusive=" + Constant.AUTH_EXCLUSIVE + "&auth_token=" + Constant.AUTH_TOKEN;
                                              Intent intent = new Intent(DetailActvity.this, DetailActvity.class);
                                              intent.putExtra("url", url);
                                              intent.putExtra("orginid",Integer.parseInt(articleid));
                                              startActivity(intent);
                                              return true;
                                          }

                                          if (requstUrl.startsWith("ios://showCategory")) {
                                              //分类
                                              String[] arr = requstUrl.split("/");
                                              //其他分类
                                              String cateid = arr[arr.length - 1];
                                              Intent intent = new Intent(DetailActvity.this, CateDetailActvity.class);
                                              intent.putExtra("cateid", Integer.parseInt(cateid));
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
        webSettings.setBlockNetworkImage(true);
        ivBottom = findViewById(R.id.iv_player_bottom);
        ivBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActvity.this, AudioDetailActvity.class);
                intent.putExtra("volumeid", volumeid);
                intent.putExtra("radiotitle", radiotitle);
                intent.putExtra("imageurl", imageUrl);
                intent.putExtra("audiourl", oringin.getMedia().getMp3().get(0));
                if (commentnum == 0) {
                    intent.putExtra("commentnum", orincommentNum);
                } else {
                    intent.putExtra("commentnum", commentnum + "");
                }
                startActivity(intent);
            }
        });

        smartRefreshLayout.autoRefresh();
//        mContent.loadUrl(url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.cricleplayer);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        Glide.with(this).load(Constant.imageurl).into(ivBottom);
        if (Constant.PLAYER != null) {
            ivBottom.setVisibility(View.VISIBLE);
        } else {
            ivBottom.setVisibility(View.GONE);
        }

        if (Constant.playState) {
            ivBottom.startAnimation(operatingAnim);
        } else {
            ivBottom.clearAnimation();
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        /*if (loadFrist){
            mContent.loadUrl(url);
            loadFrist = !loadFrist;
        }else{*/
        loadOringinDetail();
        mContent.loadUrl(url);
//        }
    }

    private void loadOringinDetail() {

        Retrofit retrofit = RetrofitClient.getRetrofit(UrlPath.base_url_api);
        newsApi = retrofit.create(NewsApi.class);
        Call<OringinDetailRes> call = newsApi.getOringinDetail(orginid, Constant.AUTH_EXCLUSIVE, Constant.AUTH_TOKEN);
        call.enqueue(new Callback<OringinDetailRes>() {
            @Override
            public void onResponse(Call<OringinDetailRes> call, Response<OringinDetailRes> response) {
                if (response.body().getStatus() == UrlPath.NET_SUCESS) {
                    oringin = response.body().getResults();
                    tvCommentNum.setText(oringin.getComments_num() + "");
                }
            }

            @Override
            public void onFailure(Call<OringinDetailRes> call, Throwable t) {

            }
        });


    }
}
