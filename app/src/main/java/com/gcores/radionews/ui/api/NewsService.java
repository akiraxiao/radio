package com.gcores.radionews.ui.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {


    @GET("basil2style")
    Call<ResponseBody> getStartPage();
}
