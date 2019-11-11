package com.antondolganov.mapapp.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.antondolganov.mapapp.R;
import com.antondolganov.mapapp.data.Markers;
import com.antondolganov.mapapp.databinding.FragmentMapBinding;
import com.antondolganov.mapapp.view.MainActivity;
import com.antondolganov.mapapp.viewmodel.LoginViewModel;
import com.antondolganov.mapapp.viewmodel.MapViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.snackbar.Snackbar;


public class FragmentMap extends Fragment implements OnMapReadyCallback {

    private LoginViewModel modelLogin;
    private MapViewModel modelMap;
    private FragmentMapBinding binding;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        modelLogin = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        modelMap = ViewModelProviders.of(getActivity()).get(MapViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        observeViewModel();
        loadRouteSegments();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbar(binding.toolbarMap, getString(R.string.app_name));
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.log_out, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logOut:
                modelLogin.deleteLoginBd();
                modelMap.statusLiveData.setValue(null);
                navController.navigate(R.id.fragmentLogin);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;
        setLineTruck();
    }

    private void setLineTruck() {
        modelMap.results.observe(getViewLifecycleOwner(), trucks -> {
            if(trucks!=null) {
                PolylineOptions line = new PolylineOptions();
                double lat=0;
                double lng=0;
                for (Markers markers : trucks.getRouteSegments().get(0).getMarkers()) {
                    lat = markers.getCoordinates().getLat();
                    lng = markers.getCoordinates().getLng();
                    line.add(new LatLng(lat, lng));
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng)));
                }
                googleMap.addPolyline(line);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 4));
            }
        });
    }

    private void loadRouteSegments() {
        String header = modelLogin.getLoginViewModel().getToken_type() + " " + modelLogin.getLoginViewModel().getAccess_token();
        modelMap.loadRouteSegments(header);
    }

    private void observeViewModel() {
        modelMap.statusLiveData.observe(getViewLifecycleOwner(), status -> {
            if (status != null)
                snackbarShow(status);
        });
    }

    private void snackbarShow(String text) {
        Snackbar.make(binding.frameLayoutMap, text, Snackbar.LENGTH_LONG).show();
    }
}
