package com.wipro.android.proficiencyexercise.view.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wipro.android.proficiencyexercise.R;
import com.wipro.android.proficiencyexercise.model.Rows;
import com.wipro.android.proficiencyexercise.view.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends BaseFragment {

    private final String TAG = DetailFragment.class.getSimpleName();
    private TextView desc;
    private ImageView image;
    private Rows rows;

    public static DetailFragment newInstance(Rows rows) {
        Bundle args = new Bundle();
        args.putSerializable("rows", rows);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rows = (Rows) getArguments().getSerializable("rows");
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
        setViewData();
        return view;
    }

    private void setViewData() {
        if (rows != null) {
            if (rows.getTitle() != null && !rows.getTitle().isEmpty()) {
                getBaseActivity().getSupportActionBar().setTitle(rows.getTitle());
            }
            if (rows.getDescription() != null && !rows.getDescription().isEmpty()) {
                desc.setText(rows.getDescription());
            }
            if (rows.getImageHref() != null && !rows.getImageHref().isEmpty()) {
                Glide.with(getBaseActivity())
                        .load(rows.getImageHref())
                        .into(image);
            }
        }
    }
}