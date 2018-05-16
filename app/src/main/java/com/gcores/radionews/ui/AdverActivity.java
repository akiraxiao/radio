package com.gcores.radionews.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.api.NewsService;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
import com.gcores.radionews.ui.model.notifiation.Notifacation;
import com.gcores.radionews.ui.model.start.AdPage;
import com.gcores.radionews.ui.resmoel.AdPageRes;
import com.gcores.radionews.ui.resmoel.NotificationRes;
import com.gcores.radionews.ui.view.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**广告
 * 页面*/
public class AdverActivity extends BaseActivity {


    private ImageView imageStart;
//    private Context mContext;

    private TextView tvTime;//计时器字幕

    private Timer timer;//计时器
    private TimerTask timerTask;//计时器任务
    private final int MAX_COUNT = 5;//最大计算时长
    private int currentCount = 5;//当前时间(秒)

    private int notification_size = 0;//未读消息数
    private int subcribe_size = 0;//订阅数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        imageStart = findViewById(R.id.image_start);
        tvTime = findViewById(R.id.tv_time);
        //直接进入主页
        tvTime.setOnClickListener((v)->{
            startActivityTrans(HomeActivity.class);
            AdverActivity.this.finishAfterTransition();
            stopCountDown();
                }

        );
        //开始获取广告页面请求
        Retrofit retrofit =  RetrofitClient.getRetrofit(UrlPath.base_url_api);
        NewsService newsService = retrofit.create(NewsService.class);

        startPageApi(newsService);
        getNotificationMessage(newsService);
        //开始倒计时任务
        startCountDown();
//        Glide.with(this).load().into(imageStart);
    }

    //获取订阅和未读信息
    private void getNotificationMessage(NewsService newsService) {
       Call<NotificationRes> call =   newsService.getNotificationMessage();
       call.enqueue(new Callback<NotificationRes>() {
           @Override
           public void onResponse(Call<NotificationRes> call, Response<NotificationRes> response) {

               if (response.body().getStatus()==UrlPath.NET_SUCESS){
                   Notifacation notificationRes =  response.body().getResults();
                   notification_size = notificationRes.getNotifications_size();
                   subcribe_size = notificationRes.getSubscriptions_size();
               }
           }

           @Override
           public void onFailure(Call<NotificationRes> call, Throwable t) {

           }
       });
    }

    private void startCountDown() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                //当前倒计时小于0或者大于0 就继续否则停止任务
                if (currentCount<=MAX_COUNT && currentCount>0)
                {
                    currentCount--;
                    runOnUiThread(()-> {
                    if (currentCount<=MAX_COUNT && currentCount>0){
                        tvTime.setText("跳过"+currentCount+"s");

                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putInt("notifications_size",notification_size);
                        bundle.putInt("subscriptions_size",subcribe_size);
                        startActivityTrans(HomeActivity.class,bundle);
                        AdverActivity.this.finishAfterTransition();
                     }
                    });
                }else{
//                    timer.cancel();
                    stopCountDown();

                }

            }
        };
        timer.schedule(timerTask,1000,1000);
    }

    private void stopCountDown() {
        if (timer!=null){
            timer.cancel();
            timerTask.cancel();
            timer = null;
            timerTask = null;
        }

    }

    private void startPageApi(NewsService newsService) {

//        Retrofit retrofit =  RetrofitClient.getRetrofit(UrlPath.base_url_api);
//        NewsService newsService =   retrofit.create(NewsService.class);
        Call<AdPageRes> call =  newsService.getStartPage();
        call.enqueue(new Callback<AdPageRes>() {
            @Override
            public void onResponse(Call<AdPageRes> call, Response<AdPageRes> response) {
//                try {
                    if (response.body().getStatus()== UrlPath.NET_SUCESS){

                        AdPage adPage = (AdPage) response.body().getResults();
                        String imagePath = UrlPath.image_base + adPage.getPath();
                        Glide.with(AdverActivity.this).load(imagePath).into(imageStart);
                    }


                    //2秒进入首页
                   /* Handler handler = new Handler();
                    handler.postDelayed(()->{
                                startActivityTrans(HomeActivity.class);
                                StartActivity.this.finishAfterTransition();
                            }
                            ,2000);*/
                /*}catch (JsonSyntaxException e ){

                }*/
            }

            @Override
            public void onFailure(Call<AdPageRes> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCountDown();

    }
}
