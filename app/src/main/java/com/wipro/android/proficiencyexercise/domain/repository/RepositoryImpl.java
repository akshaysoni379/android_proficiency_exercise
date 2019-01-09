package com.wipro.android.proficiencyexercise.domain.repository;

import com.wipro.android.proficiencyexercise.model.CanadaList;
import com.wipro.android.proficiencyexercise.netcomm.ServiceAPI;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RepositoryImpl implements Repository {
    ServiceAPI apiInterface;

    @Inject
    public RepositoryImpl(ServiceAPI apiInterface) {
        this.apiInterface = apiInterface;
    }

    @Override
    public Observable<CanadaList> getCanadaList() {
        return apiInterface.getCanadaList();
    }
}
