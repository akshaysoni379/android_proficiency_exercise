package com.wipro.android.proficiencyexercise.domain.intractor

import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList
import com.wipro.android.proficiencyexercise.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

class CanadaListUseCase @Inject
constructor(private val repository: Repository) : UseCase<CanadaList>() {

    override fun buildUseCaseObservable(): Observable<CanadaList> {
        return repository.canadaList
    }
}


