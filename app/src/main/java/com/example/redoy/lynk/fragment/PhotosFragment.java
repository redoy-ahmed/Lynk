package com.example.redoy.lynk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.adapter.AutoFitGridLayoutManager;
import com.example.redoy.lynk.adapter.RecyclerViewAdapterPhotos;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosFragment extends Fragment {

    @BindView(R.id.recycler_view_photos)
    RecyclerView mPhotosRecyclerView;

    RecyclerViewAdapterPhotos recyclerViewAdapterPhotos;
    public AutoFitGridLayoutManager layoutManager;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_photos, container, false);
        ButterKnife.bind(this, rootView);
        initializeWidgets();
        return rootView;
    }

    private void initializeWidgets() {
        ArrayList<Integer> rowListItem = getAllPhotos();
        recyclerViewAdapterPhotos = new RecyclerViewAdapterPhotos(rootView.getContext(), rowListItem);
        mPhotosRecyclerView.setAdapter(recyclerViewAdapterPhotos);

        layoutManager = new AutoFitGridLayoutManager(rootView.getContext(), 500);
        mPhotosRecyclerView.setLayoutManager(layoutManager);
    }

    private ArrayList<Integer> getAllPhotos() {
        ArrayList<Integer> allItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            allItems.add(R.drawable.item_image);
        }
        return allItems;
    }
}