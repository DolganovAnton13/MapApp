package com.antondolganov.mapapp.di;

import com.antondolganov.mapapp.di.module.ContextModule;
import com.antondolganov.mapapp.di.module.DatabaseModule;
import com.antondolganov.mapapp.repository.DataRepository;
import com.antondolganov.mapapp.di.module.NetworkModule;
import com.antondolganov.mapapp.di.module.RepositoryModule;
import com.antondolganov.mapapp.repository.DatabaseRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class, NetworkModule.class, DatabaseModule.class, ContextModule.class})
public interface AppComponent {

    DataRepository getDataRepository();
    DatabaseRepository getDatabaseRepository();
}
