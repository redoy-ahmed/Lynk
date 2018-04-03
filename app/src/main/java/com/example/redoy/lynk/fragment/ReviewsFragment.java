package com.example.redoy.lynk.fragment;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.adapter.ReviewAdapter;
import com.example.redoy.lynk.model.ReviewItem;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class ReviewsFragment extends Fragment {

    @BindView(R.id.recycler_view_photos)
    RecyclerView mPhotosRecyclerView;

    @BindView(R.id.add_review_button)
    FloatingActionButton addReviewButton;

    ArrayList<ReviewItem> reviewItems;
    public final int REQ_CODE_SPEECH_INPUT = 100;
    String resultString;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_reviews, container, false);
        ButterKnife.bind(this, rootView);
        initializeWidgets();
        return rootView;
    }

    private void initializeWidgets() {
        reviewItems = new ArrayList<>();
        reviewItems = getReviewItems();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mPhotosRecyclerView.setLayoutManager(linearLayoutManager);
        mPhotosRecyclerView.setHasFixedSize(true);
        ReviewAdapter adapter = new ReviewAdapter(getContext(), reviewItems);
        mPhotosRecyclerView.setAdapter(adapter);

        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVoiceDialog();
            }
        });
    }

    private ArrayList<ReviewItem> getReviewItems() {
        reviewItems.add(new ReviewItem("Redoy Ahmed", "1:06pm", rootView.getContext().getResources().getString(R.string.review_description)));
        reviewItems.add(new ReviewItem("Kalam Azad", "3:06pm", rootView.getContext().getResources().getString(R.string.review_description)));
        reviewItems.add(new ReviewItem("Kowsar Jony", "10:06pm", rootView.getContext().getResources().getString(R.string.review_description)));
        reviewItems.add(new ReviewItem("Sohel Rana", "1:06pm", rootView.getContext().getResources().getString(R.string.review_description)));

        return reviewItems;
    }

    private void showVoiceDialog() {
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
                    resultString = result.get(0);
                    addReviewDialog(resultString);
                }
                break;
            }
        }
    }

    private void addReviewDialog(final String resultString) {
        LayoutInflater li = LayoutInflater.from(rootView.getContext());
        View promptsView = li.inflate(R.layout.review_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = promptsView.findViewById(R.id.editTextDialogReviewMessage);
        userInput.setText(resultString);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Post",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(rootView.getContext(), "Review Posted", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}