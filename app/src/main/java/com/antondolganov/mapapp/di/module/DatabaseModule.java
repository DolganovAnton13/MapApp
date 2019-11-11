package com.antondolganov.mapapp.di.module;

import android.content.Context;

import androidx.room.Room;

import com.antondolganov.mapapp.db.AppDatabase;
import com.antondolganov.mapapp.db.dao.LoginDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class DatabaseModule {

    @Singleton
    @Provides
    AppDatabase appDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    LoginDao logintDao(AppDatabase appDatabase) {
        return appDatabase.loginDao();
    }
}