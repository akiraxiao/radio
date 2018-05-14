package com.gcores.radionews.ui.api;

import com.gcores.radionews.ui.resmoel.AdPageRes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {


    @GET("app_start_pages/active_page?auth_exclusive=dpkynzs2q0wm9o5gi1r83fcabthl4eu&auth_token=LTxNSzIS5kWskwT-jGW0Fw")
    Call<AdPageRes> getStartPage();
}
