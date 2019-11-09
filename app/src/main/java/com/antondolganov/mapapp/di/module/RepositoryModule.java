package com.antondolganov.mapapp.di.module;

import com.antondolganov.mapapp.repository.DataRepository;
import com.antondolganov.mapapp.api.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModule.class})
public class RepositoryModule {

    @Singleton
    @Provides
    DataRepository dataRepository(Api testApi) {
        return new DataRepository(testApi);
    }

}
