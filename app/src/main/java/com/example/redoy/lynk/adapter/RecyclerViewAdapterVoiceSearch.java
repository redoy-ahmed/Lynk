package com.example.redoy.lynk.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.activity.ProfileActivity;
import com.example.redoy.lynk.model.VoiceSearchItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by redoy.ahmed on 04-Mar-2018.
 */

public class RecyclerViewAdapterVoiceSearch extends RecyclerView.Adapter<RecyclerViewAdapterVoiceSearch.RecyclerViewHolderHome> {

    private ArrayList<VoiceSearchItem> itemList;
    private Context context;
    private static FragmentManager fragmentManager;

    public RecyclerViewAdapterVoiceSearch(Context context, ArrayList<VoiceSearchItem> itemList, FragmentManager fragmentManager) {
        this.itemList = itemList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerViewHolderHome onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list_voice_search, parent, false);
        RecyclerViewHolderHome rcv = new RecyclerViewHolderHome(layoutView, itemList, context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderHome holder, int position) {
        holder.itemName.setText(itemList.get(position).getTitle());
        Picasso.get().load(itemList.get(position).getFeature_img()).into(holder.itemPhoto);
        holder.itemCityName.setText(itemList.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public static class RecyclerViewHolderHome extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_textView)
        public TextView itemName;

        @BindView(R.id.list_item_imageView)
        public ImageView itemPhoto;

        @BindView(R.id.list_item_city_textView)
        public TextView itemCityName;

        @BindView(R.id.relativeLayout)
        public RelativeLayout relativeLayout;

        public RecyclerViewHolderHome(final View itemView, final ArrayList<VoiceSearchItem> itemList, final Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra("id", itemList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}