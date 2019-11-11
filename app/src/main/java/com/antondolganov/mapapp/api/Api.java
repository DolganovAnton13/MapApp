package com.antondolganov.mapapp.api;

import com.antondolganov.mapapp.data.Login;
import com.antondolganov.mapapp.data.Truck;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"})
    @POST("/api/auth")
    Call<Login> authLogin(
            @Field("grant_type") String type,
            @Field("username") String username,
            @Field("password") String password
    );



    @Headers("Accept: application/json")
    @GET("/api/v1/truck/route")
    Call<Truck> getRouteSegments(
            @Header("Authorization") String token
    );
}
