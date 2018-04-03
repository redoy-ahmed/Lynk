package com.example.redoy.lynk.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.activity.PhotosActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapterPhotos extends RecyclerView.Adapter<RecyclerViewAdapterPhotos.RecyclerViewHolderPhotos> {

    private ArrayList<Integer> itemList;
    private Context context;

    public RecyclerViewAdapterPhotos(Context context, ArrayList<Integer> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapterPhotos.RecyclerViewHolderPhotos onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list_photo, parent, false);
        RecyclerViewAdapterPhotos.RecyclerViewHolderPhotos rcv = new RecyclerViewAdapterPhotos.RecyclerViewHolderPhotos(layoutView, itemList, context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterPhotos.RecyclerViewHolderPhotos holder, int position) {
        holder.itemPhoto.setImageResource(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public static class RecyclerViewHolderPhotos extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_imageView)
        public ImageView itemPhoto;

        public RecyclerViewHolderPhotos(final View itemView, final ArrayList<Integer> itemList, final Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PhotosActivity.class);
                    intent.putIntegerArrayListExtra("images", itemList);
                    context.startActivity(intent);
                }
            });
        }
    }
}