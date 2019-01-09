package com.wipro.android.proficiencyexercise.domain.intractor;

import com.wipro.android.proficiencyexercise.domain.repository.Repository;
import com.wipro.android.proficiencyexercise.model.CanadaList;
import javax.inject.Inject;

import io.reactivex.Observable;

public class CanadaListUseCase extends UseCase<CanadaList> {
    Repository repository;

    @Inject
    public CanadaListUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<CanadaList> buildUseCaseObservable() {
        return repository.getCanadaList();
    }
}


