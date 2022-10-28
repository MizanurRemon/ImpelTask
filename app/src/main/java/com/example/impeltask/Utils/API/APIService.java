package com.example.impeltask.Utils.API;

import com.example.impeltask.Model.Top_headline_response;
import com.example.impeltask.Utils.Const.Urls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("top-headlines")
    Call<Top_headline_response> getTopHeadLines(@Query("country") String country,
                                                @Query("category") String category,
                                                @Query("apiKey") String apiKey);

}
