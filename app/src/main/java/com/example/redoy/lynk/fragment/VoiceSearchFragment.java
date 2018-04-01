package com.example.redoy.lynk.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.activity.MainActivity;
import com.example.redoy.lynk.adapter.AutoFitGridLayoutManager;
import com.example.redoy.lynk.adapter.RecyclerViewAdapterVoiceSearch;
import com.example.redoy.lynk.model.VoiceSearchItem;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Redoy on 3/30/2018.
 */

public class VoiceSearchFragment extends Fragment {

    View rootView;

    @BindView(R.id.microphone_logo_imageView)
    public ImageView microPhoneImageView;

    @BindView(R.id.hint_textView)
    public TextView hintTextView;

    @BindView(R.id.empty_textView)
    public TextView emptyTextView;

    @BindView(R.id.recycler_view_voice_search)
    public RecyclerView voiceSearchRecyclerView;

    public final int REQ_CODE_SPEECH_INPUT = 100;
    ArrayList<String> searchResult;
    public String resultText;

    public RecyclerViewAdapterVoiceSearch recyclerViewAdapterVoiceSearch;
    public AutoFitGridLayoutManager layoutManager;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_voice_search, container, false);
        ButterKnife.bind(this, rootView);
        initializeWidgets();
        initializeData();

        return rootView;
    }

    private void initializeWidgets() {
        microPhoneImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchDialog();
            }
        });
    }

    private void showSearchDialog() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(rootView.getContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {

                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    resultText = result.get(0);
                    Toast.makeText(rootView.getContext(), resultText, Toast.LENGTH_LONG).show();
                    recyclerViewAdapterVoiceSearch.getFilter().filter(resultText);
                    microPhoneImageView.setVisibility(View.GONE);
                    hintTextView.setVisibility(View.GONE);

                    if (recyclerViewAdapterVoiceSearch.getItemCount() != 0) {
                        voiceSearchRecyclerView.setVisibility(View.VISIBLE);
                    } else {
                        emptyTextView.setVisibility(View.VISIBLE);
                        emptyTextView.setText("No Data Found!");
                    }
                }
                break;
            }
        }
    }

    private void initializeData() {
        //searchResult = getIntent().getStringArrayListExtra("results");
        searchResult = new ArrayList<>();
        searchResult.add("Abc");
        searchResult.add("Kolapata Restaurant");
        searchResult.add("Bismillah Hotel");
        searchResult.add("Mehedi Hotel");
        searchResult.add("Cha Kunjo");
        searchResult.add("New Bismillah Hotel");
        searchResult.add("Marwa Kebab House");
        searchResult.add("CP");
        searchResult.add("Regency Hotel");

        ArrayList<VoiceSearchItem> rowListItem = getAllItemList(searchResult);
        recyclerViewAdapterVoiceSearch = new RecyclerViewAdapterVoiceSearch(rootView.getContext(), rowListItem, getFragmentManager());
        voiceSearchRecyclerView.setAdapter(recyclerViewAdapterVoiceSearch);

        layoutManager = new AutoFitGridLayoutManager(rootView.getContext(), 500);
        voiceSearchRecyclerView.setLayoutManager(layoutManager);
    }

    private ArrayList<VoiceSearchItem> getAllItemList(ArrayList<String> searchResult) {

        ArrayList<VoiceSearchItem> allItems = new ArrayList<>();
        for (int i = 0; i < searchResult.size(); i++) {
            allItems.add(new VoiceSearchItem(searchResult.get(i), R.drawable.a, "#09A9FF"));
        }
        return allItems;
    }
}
