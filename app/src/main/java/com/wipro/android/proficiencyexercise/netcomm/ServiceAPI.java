package com.wipro.android.proficiencyexercise.netcomm;

import com.wipro.android.proficiencyexercise.model.CanadaList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI {

    @GET(ApiConstant.END_POINT)
    Observable<CanadaList> getCanadaList();
}