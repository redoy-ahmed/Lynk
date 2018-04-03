package com.example.redoy.lynk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.redoy.lynk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HighlightsFragment extends Fragment {

    @BindView(R.id.hours_textView)
    TextView mHoursTextView;

    @BindView(R.id.brand_type_textView)
    TextView mBrandTypeTextView;

    @BindView(R.id.services_offered_textView)
    TextView mServiceOfferedTextView;

    @BindView(R.id.licenses_certifications_textView)
    TextView mLicensesCertificationTextView;

    @BindView(R.id.neighbourhood_textView)
    TextView mNeighbourhoodTextView;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_hightlights, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}