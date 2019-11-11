package com.antondolganov.mapapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.antondolganov.mapapp.data.Login;
import com.antondolganov.mapapp.db.dao.LoginDao;

@Database(entities = {Login.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LoginDao loginDao();

}