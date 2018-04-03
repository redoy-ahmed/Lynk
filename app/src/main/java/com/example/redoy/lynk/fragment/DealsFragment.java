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

public class DealsFragment extends Fragment {

    @BindView(R.id.deals_textView)
    TextView mDealsTextView;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_deals, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}