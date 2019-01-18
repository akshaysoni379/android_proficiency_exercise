package com.wipro.android.proficiencyexercise.view.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.gson.Gson
import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList
import com.wipro.android.proficiencyexercise.domain.intractor.CanadaListUseCase
import com.wipro.android.proficiencyexercise.utils.LogUtil
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ListFragmentViewModel @Inject
constructor(private val useCase: CanadaListUseCase) : ViewModel() {

    val tag = "ListFragmentViewModel"
    val apiResponse = MutableLiveData<CanadaList>()
    val loaderData = MutableLiveData<Boolean>()

    override fun onCleared() {
        loaderData.value = false
    }

    fun apiCall() {
        loaderData.value = true
        useCase.execute(object : DisposableObserver<CanadaList>() {
            override fun onNext(canadaList: CanadaList) {
                loaderData.value = false
                LogUtil.d(tag, Gson().toJson(canadaList))
                apiResponse.value = canadaList
            }

            override fun onError(e: Throwable) {
                LogUtil.d(tag, e.message!!)
                loaderData.value = false
            }

            override fun onComplete() {
                LogUtil.d(tag, " Response Complete")
            }
        })
    }
}
