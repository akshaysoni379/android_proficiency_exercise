package com.wipro.android.proficiencyexercise.domain.repository

import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList

import io.reactivex.Observable

interface Repository {

    val canadaList: Observable<CanadaList>
}
