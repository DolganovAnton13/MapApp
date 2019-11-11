package com.antondolganov.mapapp.repository;

import com.antondolganov.mapapp.api.Api;
import com.antondolganov.mapapp.data.Login;
import com.antondolganov.mapapp.data.Truck;

import retrofit2.Call;

public class DataRepository {

    private Api api;

    public DataRepository(Api api) {
        this.api = api;
    }

    public Call<Login> authLogin(String username, String password)
    {
        return api.authLogin("password",username, password);
    }

    public Call<Truck> getRouteSegments(String token)
    {
        return api.getRouteSegments(token);
    }
}
