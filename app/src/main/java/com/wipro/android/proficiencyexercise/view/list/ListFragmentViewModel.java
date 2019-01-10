package com.wipro.android.proficiencyexercise.view.list;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList;
import com.wipro.android.proficiencyexercise.domain.intractor.CanadaListUseCase;
import com.wipro.android.proficiencyexercise.utils.LogUtil;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class ListFragmentViewModel extends ViewModel {

    private final String TAG = ListFragmentViewModel.class.getSimpleName();
    public final MutableLiveData<Object> apiResponse = new MutableLiveData<>();
    public final MutableLiveData<Boolean> loaderData = new MutableLiveData<>();
    private CanadaListUseCase useCase;

    @Inject
    public ListFragmentViewModel(CanadaListUseCase canadaListUseCase) {
        this.useCase = canadaListUseCase;
    }

    @Override
    protected void onCleared() {
        loaderData.setValue(false);
    }

    public void apiCall() {
        loaderData.setValue(true);
        useCase.execute(new DisposableObserver<CanadaList>() {
            @Override
            public void onNext(CanadaList canadaList) {
                loaderData.setValue(false);
                LogUtil.INSTANCE.d(TAG, new Gson().toJson(canadaList));
                apiResponse.setValue(canadaList);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.INSTANCE.d(TAG, e.getMessage());
                apiResponse.setValue(e);
                loaderData.setValue(false);
            }

            @Override
            public void onComplete() {
                LogUtil.INSTANCE.d(TAG, " Response Complete");
            }
        });
    }
}
