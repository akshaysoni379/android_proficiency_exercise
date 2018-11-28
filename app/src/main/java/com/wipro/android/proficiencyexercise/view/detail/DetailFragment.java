package com.wipro.android.proficiencyexercise.view.detail;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wipro.android.proficiencyexercise.AppUtil.LogUtil;
import com.wipro.android.proficiencyexercise.R;
import com.wipro.android.proficiencyexercise.model.CanadaList;
import com.wipro.android.proficiencyexercise.model.Rows;
import com.wipro.android.proficiencyexercise.view.base.BaseFragment;
import com.wipro.android.proficiencyexercise.view.list.ListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends BaseFragment {

    private final String TAG = DetailFragment.class.getSimpleName();
    ListFragmentViewModel listFragmentViewModel;
    private TextView desc;
    private ImageView image;

    public static DetailFragment newInstance() {
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        desc = (TextView) view.findViewById(R.id.desc);
        image = (ImageView) view.findViewById(R.id.image);
        listFragmentViewModel = ViewModelProviders.of(this).get(ListFragmentViewModel.class);
        listFragmentViewModel.getDataFromApi();
        return view;
    }


    private void setViewData(ArrayList<Rows> rowsList) {

    }
}