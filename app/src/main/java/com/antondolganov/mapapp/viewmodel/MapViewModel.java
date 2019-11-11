package com.antondolganov.mapapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.antondolganov.mapapp.App;
import com.antondolganov.mapapp.R;
import com.antondolganov.mapapp.data.Truck;
import com.antondolganov.mapapp.network.NetworkState;
import com.antondolganov.mapapp.repository.DataRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapViewModel extends AndroidViewModel {

    private DataRepository data = App.getComponent().getDataRepository();
    private NetworkState networkState = new NetworkState(getApplication());
    public MutableLiveData<Truck> results = new MutableLiveData<>();
    public MutableLiveData<String> statusLiveData = new MutableLiveData<>();

    public MapViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadRouteSegments(String token) {
        if (networkState.isOnline()) {
            data.getRouteSegments(token).enqueue(new Callback<Truck>() {
                @Override
                public void onResponse(Call<Truck> call, Response<Truck> response) {

                    if (response.code() == 200) {
                        results.postValue(response.body());
                    } else {
                        statusLiveData.setValue("Ошибка");
                    }
                }

                @Override
                public void onFailure(Call<Truck> call, Throwable t) {
                    statusLiveData.postValue(t.getMessage());
                }
            });
        }
        else
        {
            statusLiveData.postValue(getApplication().getString(R.string.no_internet_connection));
        }
    }
}
