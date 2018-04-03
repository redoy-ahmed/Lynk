package com.example.redoy.lynk.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.activity.BusinessInformationRegistrationActivity;
import com.example.redoy.lynk.model.VoiceSearchItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by redoy.ahmed on 04-Mar-2018.
 */

public class RecyclerViewAdapterVoiceSearch extends RecyclerView.Adapter<RecyclerViewAdapterVoiceSearch.RecyclerViewHolderHome> implements Filterable {

    private ArrayList<VoiceSearchItem> itemList;
    private ArrayList<VoiceSearchItem> itemListFiltered;
    private Context context;
    private static FragmentManager fragmentManager;

    public RecyclerViewAdapterVoiceSearch(Context context, ArrayList<VoiceSearchItem> itemList, FragmentManager fragmentManager) {
        this.itemList = itemList;
        this.itemListFiltered = itemList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerViewHolderHome onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list_voice_search, parent, false);
        RecyclerViewHolderHome rcv = new RecyclerViewHolderHome(layoutView, context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderHome holder, int position) {
        holder.itemName.setText(itemListFiltered.get(position).getName());
        holder.itemPhoto.setImageResource(itemListFiltered.get(position).getPhoto());
        holder.relativeLayout.setBackgroundColor(Color.parseColor(itemListFiltered.get(position).getColor()));
    }

    @Override
    public int getItemCount() {
        return this.itemListFiltered.size();
    }

    public static class RecyclerViewHolderHome extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_textView)
        public TextView itemName;

        @BindView(R.id.list_item_imageView)
        public ImageView itemPhoto;
        @BindView(R.id.relativeLayout)
        public RelativeLayout relativeLayout;

        public RecyclerViewHolderHome(final View itemView, final Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, BusinessInformationRegistrationActivity.class));
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    itemListFiltered = itemList;
                } else {
                    ArrayList<VoiceSearchItem> filteredList = new ArrayList<>();
                    for (VoiceSearchItem row : itemList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    itemListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = itemListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                itemListFiltered = (ArrayList<VoiceSearchItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

