package com.wipro.android.proficiencyexercise.view.list;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wipro.android.proficiencyexercise.R;
import com.wipro.android.proficiencyexercise.WiproApp;
import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList;
import com.wipro.android.proficiencyexercise.data.remote.response.Row;
import com.wipro.android.proficiencyexercise.databinding.ListFragmentBinding;
import com.wipro.android.proficiencyexercise.utils.GlobalViewModelFactory;
import com.wipro.android.proficiencyexercise.utils.LogUtil;
import com.wipro.android.proficiencyexercise.utils.Utils;
import com.wipro.android.proficiencyexercise.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

public class ListFragment extends BaseFragment {

    private final String TAG = ListFragment.class.getSimpleName();
    private ListFragmentViewModel listFragmentViewModel;
    private ListFragmentBinding binding;

    @Inject
    GlobalViewModelFactory<ListFragmentViewModel> modelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setLiveDataObserver();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);
        ((WiproApp) Objects.requireNonNull(getActivity()).getApplicationContext()).getAppComponent().inject(this);
        listFragmentViewModel = ViewModelProviders.of(this, modelFactory).get(ListFragmentViewModel.class);
        binding.setLifecycleOwner(this);

        binding.swipeContainer.setOnRefreshListener(this::apiCall);
        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        apiCall();

        return binding.getRoot();
    }

    private void apiCall() {
        if (Utils.INSTANCE.checkNetwork(getBaseActivity())) {
            listFragmentViewModel.apiCall();
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.networkErrorTv.setVisibility(View.GONE);
        } else {
            binding.recyclerView.setVisibility(View.GONE);
            binding.networkErrorTv.setVisibility(View.VISIBLE);
            hideSwipeIndicator();
        }
    }

    private void setLiveDataObserver() {
        listFragmentViewModel.apiResponse.observe(this, var -> {
            try {
                CanadaList canadaList = (CanadaList) var;
                if (canadaList != null && canadaList.getRows() != null && canadaList.getRows().size() > 0) {
                    LogUtil.INSTANCE.e(TAG, "size: " + canadaList.getRows().size());
                    setViewData(canadaList.getRows());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        listFragmentViewModel.loaderData.observe(this, isSHow -> {
            if (getDialog() != null) {
                if (isSHow != null) {
                    if (isSHow) {
                        getDialog().show();
                    } else {
                        getDialog().hide();
                        hideSwipeIndicator();
                    }
                }
            }
        });
    }

    private void hideSwipeIndicator() {
        if (binding.swipeContainer != null && binding.swipeContainer.isShown()) {
            binding.swipeContainer.setRefreshing(false);
        }
    }

    private void setViewData(ArrayList<Row> rowsList) {
        ListAdapter mAdapter = new ListAdapter(getBaseActivity(), rowsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setAdapter(mAdapter);
    }
}