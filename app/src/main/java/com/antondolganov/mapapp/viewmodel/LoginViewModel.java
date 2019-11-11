package com.antondolganov.mapapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.antondolganov.mapapp.App;
import com.antondolganov.mapapp.R;
import com.antondolganov.mapapp.data.Login;
import com.antondolganov.mapapp.network.NetworkState;
import com.antondolganov.mapapp.repository.DataRepository;
import com.antondolganov.mapapp.repository.DatabaseRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private DataRepository data = App.getComponent().getDataRepository();
    private DatabaseRepository database = App.getComponent().getDatabaseRepository();
    public MutableLiveData<String> statusLiveData = new MutableLiveData<>();
    public MutableLiveData<String> resultLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    private Login login = null;
    private NetworkState networkState = new NetworkState(getApplication());
    private Observer<Login> observer;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        getLoginBd();
    }

    public void authLogin(String username, String password) {
        if (networkState.isOnline()) {
            loadingLiveData.postValue(true);
            data.authLogin(username, password).enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    if (response.code() == 200) {
                        statusLiveData.setValue("Карта загружается");
                        insertLoginBd(response.body());
                        setLoginViewModel(response.body());
                        resultLiveData.setValue("RESULT_OK");
                    }
                    if (response.code() == 400) {
                        statusLiveData.setValue(getErrorMessage(response));
                    }
                    loadingLiveData.postValue(false);
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    statusLiveData.setValue(t.getMessage());
                    loadingLiveData.postValue(false);
                }
            });
        } else {
            statusLiveData.postValue(getApplication().getString(R.string.no_internet_connection));
        }
    }

    private String getErrorMessage(Response<Login> response) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response.errorBody().string());
            return jsonObject.getString("error") + getApplication().getString(R.string.incorrect_data);

        } catch (JSONException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public Login getLoginViewModel() {
        return login;
    }

    private void setLoginViewModel(Login login) {
        this.login = login;
    }

    private void getLoginBd()
    {
        database.getLogin().observeForever(observer= login -> {
            if(login!=null) {
                statusLiveData.setValue("Карта загружается");
                setLoginViewModel(login);
                resultLiveData.setValue("RESULT_OK");
            }
        });
    }

    private void insertLoginBd(Login login)
    {
        database.insertLogin(login);
    }

    public void deleteLoginBd()
    {
        database.deleteLogin();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        database.getLogin().removeObserver(observer);
    }
}
