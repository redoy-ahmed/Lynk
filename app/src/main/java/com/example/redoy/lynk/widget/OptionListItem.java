package com.example.redoy.lynk.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.example.redoy.lynk.R;


public class OptionListItem {
    public final TextView textView;
    public View view;

    @SuppressLint({"InflateParams"})
    public OptionListItem(Context context, String str, OnClickListener onClickListener) {
        this.view = LayoutInflater.from(context).inflate(R.layout.widget_option_item, null);
        textView = this.view.findViewById(R.id.option_item_widget_notification);
        TextView textView = this.view.findViewById(R.id.option_item_widget_title);
        this.textView.setVisibility(View.GONE);
        textView.setText(str);
        this.view.setOnClickListener(onClickListener);
    }

    public void m1858a() {
        this.view.findViewById(R.id.option_item_widget_image_view_chevron).setVisibility(View.GONE);
    }
}
