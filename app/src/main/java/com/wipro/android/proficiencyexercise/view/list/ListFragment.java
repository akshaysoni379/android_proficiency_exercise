package com.wipro.android.proficiencyexercise.view.list;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wipro.android.proficiencyexercise.AppUtil.Utils;
import com.wipro.android.proficiencyexercise.view.base.BaseFragment;
import com.wipro.android.proficiencyexercise.AppUtil.LogUtil;
import com.wipro.android.proficiencyexercise.R;
import com.wipro.android.proficiencyexercise.model.CanadaList;
import com.wipro.android.proficiencyexercise.model.Rows;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseFragment {

    private final String TAG = ListFragment.class.getSimpleName();
    ListFragmentViewModel listFragmentViewModel;
    private RecyclerView recyclerView;
    private TextView networkErrorTv;
    private SwipeRefreshLayout swipeContainer;

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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        networkErrorTv = (TextView) view.findViewById(R.id.network_error_tv);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        listFragmentViewModel = ViewModelProviders.of(this).get(ListFragmentViewModel.class);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCall();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        apiCall();

        return view;
    }

    private void apiCall() {
        if (Utils.checkNetwork(getBaseActivity())) {
            listFragmentViewModel.apiCall();
            recyclerView.setVisibility(View.VISIBLE);
            networkErrorTv.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            networkErrorTv.setVisibility(View.VISIBLE);
            if (swipeContainer != null && swipeContainer.isShown()) {
                swipeContainer.setRefreshing(false);
            }
        }
    }

    private void setLiveDataObserval() {

        listFragmentViewModel.apiResponse.observe(this, var -> {
            try {
                CanadaList canadaList = (CanadaList) var;
                if (canadaList != null && canadaList.getRows() != null && canadaList.getRows().size() > 0) {
                    LogUtil.e(TAG, "size: " + canadaList.getRows().size());
                    setViewData(canadaList.getRows());
                }
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
                    if (swipeContainer != null && swipeContainer.isShown()) {
                        swipeContainer.setRefreshing(false);
                    }
                }
            }
        });
    }

    private void setViewData(ArrayList<Rows> rowsList) {
        ListAdapter mAdapter = new ListAdapter(getBaseActivity(), rowsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}