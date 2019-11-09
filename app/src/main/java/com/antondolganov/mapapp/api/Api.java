package com.antondolganov.mapapp.api;

import com.antondolganov.mapapp.data.Login;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
}
