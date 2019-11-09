package com.antondolganov.mapapp.di.module;

import com.antondolganov.mapapp.api.Api;
import com.antondolganov.mapapp.di.module.ContextModule;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    public Api testApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    public Retrofit retrofit(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl("http://zapravkimanagement.staging.wellsoft.pro")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    public RxJava2CallAdapterFactory rxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }
}