package com.example.redoy.lynk.application;


import com.example.redoy.lynk.model.Example;
import com.example.redoy.lynk.model.LogIn;
import com.example.redoy.lynk.model.LogInResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface RetrofitLynk {

    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyDN7RJFmImYAca96elyZlE5s_fhX-MMuhk")
    Call<Example> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);

    @POST("auth/login")
    Call<LogInResponse> getLogInOutput(@Body LogIn login);
}
