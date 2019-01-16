package com.wipro.android.proficiencyexercise.view.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.google.gson.Gson
import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList
import com.wipro.android.proficiencyexercise.domain.intractor.CanadaListUseCase
import com.wipro.android.proficiencyexercise.utils.LogUtil

import javax.inject.Inject

import io.reactivex.observers.DisposableObserver

class ListFragmentViewModel @Inject
constructor(private val useCase: CanadaListUseCase) : ViewModel() {

    private val TAG = ListFragmentViewModel::class.java.simpleName
    val apiResponse = MutableLiveData<Any>()
    val loaderData = MutableLiveData<Boolean>()

    override fun onCleared() {
        loaderData.value = false
    }

    fun apiCall() {
        loaderData.setValue(true)
        useCase.execute(object : DisposableObserver<CanadaList>() {
            override fun onNext(canadaList: CanadaList) {
                loaderData.setValue(false)
                LogUtil.d(TAG, Gson().toJson(canadaList))
                apiResponse.setValue(canadaList)
            }

            override fun onError(e: Throwable) {
                LogUtil.d(TAG, e.message!!)
                apiResponse.setValue(e)
                loaderData.setValue(false)
            }

            override fun onComplete() {
                LogUtil.d(TAG, " Response Complete")
            }
        })
    }
}
