package com.wipro.android.proficiencyexercise.data.remote.api

import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList
import com.wipro.android.proficiencyexercise.utils.Constants
import io.reactivex.Observable
import retrofit2.http.GET

interface ServiceAPI {

    @get:GET(Constants.END_POINT)
    val canadaList: Observable<CanadaList>
}