package com.wipro.android.proficiencyexercise.domain.intractor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T> internal constructor() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun execute(observer: DisposableObserver<T>) {
        val observable = this.buildUseCaseObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        disposables.add(observable.subscribeWith(observer))
    }

    internal abstract fun buildUseCaseObservable(): Observable<T>
}
