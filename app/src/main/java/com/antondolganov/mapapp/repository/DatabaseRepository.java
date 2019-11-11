package com.antondolganov.mapapp.repository;

import androidx.lifecycle.LiveData;

import com.antondolganov.mapapp.data.Login;
import com.antondolganov.mapapp.db.dao.LoginDao;

import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class DatabaseRepository {

    private LoginDao loginDao;

    public DatabaseRepository(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public LiveData<Login> getLogin() {
        return loginDao.getLogin();
    }

    public void insertLogin(final Login login)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                loginDao.deleteAll();
                loginDao.insert(login);
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void deleteLogin()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                loginDao.deleteAll();
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }
}
