package com.wipro.android.proficiencyexercise.view;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wipro.android.proficiencyexercise.AppUtil.BaseFragment;
import com.wipro.android.proficiencyexercise.AppUtil.LogUtil;
import com.wipro.android.proficiencyexercise.R;
import com.wipro.android.proficiencyexercise.model.CanadaList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseFragment {

    private final String TAG = ListFragment.class.getSimpleName();
    ListFragmentViewModel listFragmentViewModel;

    public static ListFragment newInstance() {
        Bundle args = new Bundle();
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setLiveDataObserval();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        listFragmentViewModel = ViewModelProviders.of(this).get(ListFragmentViewModel.class);
        listFragmentViewModel.getDataFromApi();
        return view;
    }


    private void setLiveDataObserval() {

        listFragmentViewModel.apiResponse.observe(this, var -> {
            try {
                CanadaList canadaList = (CanadaList) var;
                if (canadaList != null && canadaList.getRows() != null && canadaList.getRows().size() > 0)
                    LogUtil.e(TAG, "size: " + canadaList.getRows().size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        listFragmentViewModel.loaderData.observe(this, isSHow -> {
            if (dialog != null) {
                if (isSHow) {
                    dialog.show();
                } else {
                    dialog.hide();
                }
            }
        });
    }

    private void setViewData() {

    }
}