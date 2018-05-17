package com.gcores.radionews.ui.api;

import com.gcores.radionews.ui.resmoel.AdPageRes;
import com.gcores.radionews.ui.resmoel.BannerRes;
import com.gcores.radionews.ui.resmoel.NotificationRes;
import com.gcores.radionews.ui.resmoel.TopRes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {


    @GET("app_start_pages/active_page?auth_exclusive=dpkynzs2q0wm9o5gi1r83fcabthl4eu&auth_token=LTxNSzIS5kWskwT-jGW0Fw")
    Call<AdPageRes> getStartPage();


    @GET("all_notifications_size?auth_exclusive=dpkynzs2q0wm9o5gi1r83fcabthl4eu&auth_token=LTxNSzIS5kWskwT-jGW0Fw")
    Call<NotificationRes> getNotificationMessage();


    @GET("originals/home_slideshow?auth_exclusive=dpkynzs2q0wm9o5gi1r83fcabthl4eu&auth_token=LTxNSzIS5kWskwT-jGW0Fw")
    Call<BannerRes> getBanner();

    //头条
    @GET("originals/home?page=1&auth_exclusive=dpkynzs2q0wm9o5gi1r83fcabthl4eu&auth_token=LTxNSzIS5kWskwT-jGW0Fw")
    Call<TopRes> getTopNews();
}
