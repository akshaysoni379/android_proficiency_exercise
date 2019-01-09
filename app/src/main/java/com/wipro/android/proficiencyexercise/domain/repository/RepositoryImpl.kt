package com.wipro.android.proficiencyexercise.domain.repository

import com.wipro.android.proficiencyexercise.data.remote.api.ServiceAPI
import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList

import javax.inject.Inject

import io.reactivex.Observable

class RepositoryImpl @Inject
constructor(private val apiInterface: ServiceAPI) : Repository {

    override val canadaList: Observable<CanadaList>
        get() = apiInterface.canadaList
}
