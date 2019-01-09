package com.wipro.android.proficiencyexercise.domain.repository;

import com.wipro.android.proficiencyexercise.model.CanadaList;

import io.reactivex.Observable;

public interface Repository {

    Observable<CanadaList> getCanadaList();
}
