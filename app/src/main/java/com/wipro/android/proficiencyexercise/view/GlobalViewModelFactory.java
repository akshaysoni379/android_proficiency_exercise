package com.wipro.android.proficiencyexercise.view;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.wipro.android.proficiencyexercise.AppUtil.LogUtil;

import javax.inject.Inject;

public class GlobalViewModelFactory<T> implements ViewModelProvider.Factory {

    private static final String TAG = GlobalViewModelFactory.class.getSimpleName();
    public T viewModel;

    @Inject
    GlobalViewModelFactory(T viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        LogUtil.e(TAG, "create: ");
        return (T) viewModel;

    }
}