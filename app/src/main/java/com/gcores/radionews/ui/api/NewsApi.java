package com.gcores.radionews.ui.api;

import com.gcores.radionews.ui.resmoel.AdPageRes;
import com.gcores.radionews.ui.resmoel.ArticleRes;
import com.gcores.radionews.ui.resmoel.BannerRes;
import com.gcores.radionews.ui.resmoel.CateMenuHeadRes;
import com.gcores.radionews.ui.resmoel.CateRes;
import com.gcores.radionews.ui.resmoel.NotificationRes;
import com.gcores.radionews.ui.resmoel.RadioRes;
import com.gcores.radionews.ui.resmoel.TopRes;
import com.gcores.radionews.ui.resmoel.VideoRes;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsApi {


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


    //视频
    @GET("categories/8/originals")
    Call<VideoRes> getVideos(@Query("page") int page, @Query("auth_exclusive") String auth_exclusive, @Query("auth_token")String auth_token);

    //文章
    @GET("categories/1/originals")
    Call<ArticleRes> getArticle(@Query("page") int page, @Query("auth_exclusive") String auth_exclusive, @Query("auth_token")String auth_token);


    //新闻
    @GET("categories/2/originals")
    Call<ArticleRes> getHomeNews(@Query("page") int page, @Query("auth_exclusive") String auth_exclusive, @Query("auth_token")String auth_token);


    //分类
    @GET("categories")
    Call<CateRes> getCate( @Query("auth_exclusive") String auth_exclusive, @Query("auth_token")String auth_token);

    //分类详情
//    https://www.g-cores.com/api/categories/2?auth_exclusive=dpkynzs2q0wm9o5gi1r83fcabthl4eu&auth_token=LTxNSzIS5kWskwT-jGW0Fw
    @GET("categories/{cateid}")
    Observable<CateMenuHeadRes> getCateDetailHead(@Path("cateid") int cateid, @Query("auth_exclusive") String auth_exclusive, @Query("auth_token")String auth_token);

    @GET("categories/{cateid}/originals")
    Observable<ArticleRes> getCateDetailList(@Path("cateid") int cateid, @Query("page") int page, @Query("auth_exclusive") String auth_exclusive, @Query("auth_token")String auth_token);

}
