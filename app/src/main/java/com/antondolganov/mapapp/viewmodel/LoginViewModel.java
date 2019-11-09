package com.antondolganov.mapapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.antondolganov.mapapp.App;
import com.antondolganov.mapapp.R;
import com.antondolganov.mapapp.data.Login;
import com.antondolganov.mapapp.network.NetworkState;
import com.antondolganov.mapapp.repository.DataRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private DataRepository data = App.getComponent().getDataRepository();
    public MutableLiveData<String> statusLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    private Login login = null;
    private NetworkState networkState = new NetworkState(getApplication());

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void authLogin(String username, String password) {
        if (networkState.isOnline()) {
            loadingLiveData.postValue(true);
            data.authLogin(username, password).enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    if (response.code() == 200) {
                        setLogin(response.body());
                        statusLiveData.postValue("RESULT_OK");
                    }
                    if (response.code() == 400) {
                        statusLiveData.postValue(getErrorMessage(response));
                    }
                    loadingLiveData.postValue(false);
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    statusLiveData.postValue(t.getMessage());
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

    public Login getLogin() {
        return login;
    }

    private void setLogin(Login login) {
        this.login = login;
    }
}
