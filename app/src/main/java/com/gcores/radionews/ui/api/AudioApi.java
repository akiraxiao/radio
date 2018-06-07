package com.gcores.radionews.ui.api;

import com.gcores.radionews.ui.resmoel.TimeLineRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AudioApi {
    //分类
    @GET("volumes/{volumesid}/timelines?")
    Call<TimeLineRes> getVolumeTimeLine(@Path("volumesid") int volumesid, @Query("auth_exclusive") String auth_exclusive, @Query("auth_token")String auth_token);
}
