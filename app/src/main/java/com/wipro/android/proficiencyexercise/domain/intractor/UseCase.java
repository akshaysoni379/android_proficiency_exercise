package com.wipro.android.proficiencyexercise.domain.intractor;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T> {
    CompositeDisposable disposables;

    public UseCase() {
        disposables = new CompositeDisposable();
    }

    public void execute(DisposableObserver<T> observer) {
        Observable<T> observable = this.buildUseCaseObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposables.add(observable.subscribeWith(observer));
    }

    abstract Observable<T> buildUseCaseObservable();

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.clear();
        }
    }
}
