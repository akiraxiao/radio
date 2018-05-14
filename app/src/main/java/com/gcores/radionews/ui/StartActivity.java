package com.gcores.radionews.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.api.NewsService;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
import com.gcores.radionews.ui.model.start.StartPage;
import com.gcores.radionews.ui.resmoel.StartPageRes;
import com.gcores.radionews.ui.view.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**开始
 * 页面*/
public class StartActivity extends BaseActivity {


    private ImageView imageStart;
//    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        imageStart = findViewById(R.id.image_start);

        startPageApi();

//        Glide.with(this).load().into(imageStart);
    }

    private void startPageApi() {

        Retrofit retrofit =  RetrofitClient.getRetrofit(UrlPath.base_url);
        NewsService newsService =   retrofit.create(NewsService.class);
        Call<StartPageRes> call =  newsService.getStartPage();
        call.enqueue(new Callback<StartPageRes>() {
            @Override
            public void onResponse(Call<StartPageRes> call, Response<StartPageRes> response) {
//                try {
                    if (response.body().getStatus()== UrlPath.NET_SUCESS){

                        StartPage startPage = (StartPage) response.body().getResults();
                        String imagePath = UrlPath.image_base + startPage.getPath();
                        Glide.with(StartActivity.this).load(imagePath).into(imageStart);
                    }




                    //2秒进入首页
                    Handler handler = new Handler();
                    handler.postDelayed(()->{
                                startActivityTrans(HomeActivity.class);
                                StartActivity.this.finishAfterTransition();
                            }
                            ,2000);
                /*}catch (JsonSyntaxException e ){

                }*/
            }

            @Override
            public void onFailure(Call<StartPageRes> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }

}
