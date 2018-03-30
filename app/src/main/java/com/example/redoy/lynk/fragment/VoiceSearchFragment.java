package com.example.redoy.lynk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.activity.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by Redoy on 3/30/2018.
 */

public class VoiceSearchFragment extends Fragment {
    View rootView;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_voice_search, container, false);

        ButterKnife.bind(this, rootView);

        initializeWidgets();
        initializeData();

        return rootView;
    }

    private void initializeWidgets() {

    }

    private void initializeData() {
    }
}