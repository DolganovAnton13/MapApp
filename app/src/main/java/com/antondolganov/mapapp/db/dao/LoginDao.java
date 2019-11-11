package com.antondolganov.mapapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.antondolganov.mapapp.data.Login;

@Dao
public interface LoginDao {

    @Query("SELECT * FROM Login")
    LiveData<Login> getLogin();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Login login);

    @Update
    void update(Login login);

    @Delete
    void delete(Login login);

    @Query("DELETE FROM Login")
    void deleteAll();
}
