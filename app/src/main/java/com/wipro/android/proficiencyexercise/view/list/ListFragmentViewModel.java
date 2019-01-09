package com.wipro.android.proficiencyexercise.view.list;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.wipro.android.proficiencyexercise.AppUtil.LogUtil;
import com.wipro.android.proficiencyexercise.WiproApp;
import com.wipro.android.proficiencyexercise.domain.intractor.CanadaListUseCase;
import com.wipro.android.proficiencyexercise.model.CanadaList;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        /*Call<CanadaList> call = WiproApp.getServiceAPI().getData();
        call.enqueue(new Callback<CanadaList>() {
            @Override
            public void onResponse(Call<CanadaList> call, Response<CanadaList> response) {
                loaderData.setValue(false);
                CanadaList canadaList = response.body();
                LogUtil.d(TAG, new Gson().toJson(canadaList));
                apiResponse.setValue(canadaList);
            }

            @Override
            public void onFailure(Call<CanadaList> call, Throwable t) {
                loaderData.setValue(false);
            }
        });*/


        useCase.execute(new DisposableObserver<CanadaList>() {
            @Override
            public void onNext(CanadaList canadaList) {
                loaderData.setValue(false);
                LogUtil.d(TAG, new Gson().toJson(canadaList));
                apiResponse.setValue(canadaList);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, e.getMessage());
                apiResponse.setValue(e);
                loaderData.setValue(false);
            }

            @Override
            public void onComplete() {
                LogUtil.d(TAG, " Response Complete");
            }
        });
    }
}
