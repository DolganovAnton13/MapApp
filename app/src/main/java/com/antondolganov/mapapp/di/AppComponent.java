package com.antondolganov.mapapp.di;

import com.antondolganov.mapapp.di.module.ContextModule;
import com.antondolganov.mapapp.repository.DataRepository;
import com.antondolganov.mapapp.di.module.NetworkModule;
import com.antondolganov.mapapp.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class, NetworkModule.class, ContextModule.class})
public interface AppComponent {

    DataRepository getDataRepository();
}
