package com.antondolganov.mapapp.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.antondolganov.mapapp.R;
import com.antondolganov.mapapp.databinding.FragmentMapBinding;
import com.antondolganov.mapapp.viewmodel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;


public class FragmentMap extends Fragment {

    private LoginViewModel model;
    private FragmentMapBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);

    }
}
