package com.antondolganov.mapapp.di.module;

import com.antondolganov.mapapp.db.dao.LoginDao;
import com.antondolganov.mapapp.repository.DataRepository;
import com.antondolganov.mapapp.api.Api;
import com.antondolganov.mapapp.repository.DatabaseRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModule.class, DatabaseModule.class})
public class RepositoryModule {

    @Singleton
    @Provides
    DataRepository dataRepository(Api testApi) {
        return new DataRepository(testApi);
    }

    @Singleton
    @Provides
    DatabaseRepository databaseRepository(LoginDao loginDao) {
        return new DatabaseRepository(loginDao);
    }
}
