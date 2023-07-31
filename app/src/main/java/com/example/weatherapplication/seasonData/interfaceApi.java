package com.example.weatherapplication.seasonData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface interfaceApi {

    @GET("/data/2.5/weather")
    Call<mausamData> getData(
            @Query("q") String q,
            @Query("appid") String APIKEY,
            @Query("units") String units
    );

}
