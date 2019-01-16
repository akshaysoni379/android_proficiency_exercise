package com.wipro.android.proficiencyexercise.domain.repository

import com.wipro.android.proficiencyexercise.data.remote.api.ServiceAPI
import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject
constructor(val apiInterface: ServiceAPI) : Repository {

    override val canadaList: Observable<CanadaList>
        get() = apiInterface.canadaList
}
