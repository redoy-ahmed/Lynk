package com.example.redoy.lynk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.model.ReviewItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewAdapterViewHolder> {

    private List<ReviewItem> reviewItems;
    private Context context;

    public ReviewAdapter(Context context, List<ReviewItem> reviewItems) {
        this.context = context;
        this.reviewItems = reviewItems;
    }

    @Override
    public ReviewAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_review, parent, false);
        return new ReviewAdapter.ReviewAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewAdapterViewHolder holder, int position) {
        ReviewItem profileItem = reviewItems.get(position);

        holder.mUserNameTextView.setText(profileItem.getName());
        holder.mTimeStampTextView.setText(profileItem.getTimestamp());
        holder.mDescriptionTextView.setText(String.valueOf(profileItem.getDescription()));
    }

    @Override
    public int getItemCount() {
        return reviewItems == null ? 0 : reviewItems.size();
    }

    public static class ReviewAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_name_TextView)
        TextView mUserNameTextView;

        @BindView(R.id.timestamp_TextView)
        TextView mTimeStampTextView;

        @BindView(R.id.description_TextView)
        TextView mDescriptionTextView;

        public ReviewAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}