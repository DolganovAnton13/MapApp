package com.antondolganov.mapapp;

import android.app.Application;

import com.antondolganov.mapapp.di.AppComponent;
import com.antondolganov.mapapp.di.DaggerAppComponent;
import com.antondolganov.mapapp.di.module.ContextModule;

public class App extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
