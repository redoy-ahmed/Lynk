package com.example.redoy.lynk.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.model.ProfileResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class DealsFragment extends Fragment {

    @BindView(R.id.deals_textView)
    TextView mDealsTextView;

    private ArrayList<ProfileResponse> profileResponses;

    View rootView;

    @SuppressLint("ValidFragment")
    public DealsFragment(ArrayList<ProfileResponse> profileResponses) {
        this.profileResponses = profileResponses;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_deals, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}