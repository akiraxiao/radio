package com.gcores.radionews.ui.api;

import com.gcores.radionews.ui.resmoel.AdPageRes;
import com.gcores.radionews.ui.resmoel.BannerRes;
import com.gcores.radionews.ui.resmoel.NotificationRes;
import com.gcores.radionews.ui.resmoel.RadioRes;
import com.gcores.radionews.ui.resmoel.TopRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {


    @GET("app_start_pages/active_page")
    Call<AdPageRes> getStartPage(@Query("auth_exclusive") String auth_exclusive,@Query("auth_token")String auth_token);


    @GET("all_notifications_size")
    Call<NotificationRes> getNotificationMessage(@Query("auth_exclusive") String auth_exclusive,@Query("auth_token")String auth_token);


    @GET("originals/home_slideshow")
    Call<BannerRes> getBanner(@Query("auth_exclusive") String auth_exclusive,@Query("auth_token")String auth_token);

    //头条
    @GET("originals/home")
    Call<TopRes> getTopNews(@Query("page") int page,@Query("auth_exclusive") String auth_exclusive,@Query("auth_token")String auth_token);

    //电台
    @GET("categories/9/originals")
    Call<RadioRes> getRadios(@Query("page") int page, @Query("auth_exclusive") String auth_exclusive, @Query("auth_token")String auth_token);

}
