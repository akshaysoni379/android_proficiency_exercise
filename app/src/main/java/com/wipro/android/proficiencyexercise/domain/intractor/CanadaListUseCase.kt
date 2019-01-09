package com.wipro.android.proficiencyexercise.domain.intractor

import com.wipro.android.proficiencyexercise.domain.repository.Repository
import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList

import javax.inject.Inject

import io.reactivex.Observable

class CanadaListUseCase @Inject
constructor(private val repository: Repository) : UseCase<CanadaList>() {

    internal override fun buildUseCaseObservable(): Observable<CanadaList> {
        return repository.canadaList
    }
}


