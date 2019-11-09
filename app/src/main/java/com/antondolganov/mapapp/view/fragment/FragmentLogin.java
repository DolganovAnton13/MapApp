package com.antondolganov.mapapp.view.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antondolganov.mapapp.R;
import com.antondolganov.mapapp.databinding.FragmentLoginBinding;
import com.antondolganov.mapapp.view.MainActivity;
import com.antondolganov.mapapp.viewmodel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {

    private FragmentLoginBinding binding;
    private NavController navController;
    private LoginViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setModel(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbar(binding.toolbarLogin, getString(R.string.app_name));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        observeViewModel();
    }

    public void onButtonLoginClick() {
        String username = binding.textLogin.getText().toString();
        String password = binding.textPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            snackbarShow(getString(R.string.empty_field));
        } else {
            model.authLogin(username, password);
        }
    }

    private void observeViewModel() {
        model.statusLiveData.observe(getViewLifecycleOwner(), status ->
        {
            if (status != null) {
                if (status == "RESULT_OK")
                {
                    //navController.navigate(R.id.fragmentMap);
                }
                else
                    snackbarShow(status);
            }
        });

        model.loadingLiveData.observe(getViewLifecycleOwner(), flag -> {
            if (flag != null)
                showLoading(flag);
        });
    }

    private void snackbarShow(String text) {
        Snackbar.make(binding.activityLogin, text, Snackbar.LENGTH_LONG).show();
    }

    private void showLoading(Boolean isShow) {
        if (isShow) {
            binding.progressBarLogin.setVisibility(View.VISIBLE);
            binding.buttonLogin.setVisibility(View.INVISIBLE);
        } else {
            binding.progressBarLogin.setVisibility(View.INVISIBLE);
            binding.buttonLogin.setVisibility(View.VISIBLE);
        }
    }
}
