package com.example.redoy.lynk.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.activity.NotificationSettings;
import com.example.redoy.lynk.activity.SignInSignUpActivity;
import com.example.redoy.lynk.widget.OptionListItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Redoy on 3/30/2018.
 */

public class FragmentSettings extends Fragment {

    View rootView;
    private Context context;

    @BindView(R.id.layoutOptionsList)
    LinearLayout linearLayout;

    class ClassForNotificationSettingsActivity implements View.OnClickListener {
        final FragmentSettings fragmentSettings;

        ClassForNotificationSettingsActivity(FragmentSettings fragmentSettings) {
            this.fragmentSettings = fragmentSettings;
        }
        public void onClick(View view) {
            fragmentSettings.startActivity(new Intent(fragmentSettings.getActivity(), NotificationSettings.class));
        }
    }

    class ClassForLogInSignUp implements View.OnClickListener {
        final FragmentSettings fragmentSettings;

        ClassForLogInSignUp(FragmentSettings fragmentSettings) {
            this.fragmentSettings = fragmentSettings;
        }

        public void onClick(View view) {
            fragmentSettings.startActivity(new Intent(fragmentSettings.getActivity(), SignInSignUpActivity.class));
        }
    }

    class ClassForAboutActivity implements View.OnClickListener {
        final FragmentSettings fragmentSettings;

        ClassForAboutActivity(FragmentSettings fragmentSettings) {
            this.fragmentSettings = fragmentSettings;
        }

        public void onClick(View view) {
            //fragmentSettings.startActivity(new Intent(moreOptionsFragment.getActivity(), ProfileActivity.class));
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, rootView);
        initializeWidgets();
        initializeData();

        return rootView;
    }

    private void initializeWidgets() {
        ButterKnife.bind(this, rootView);
        context = rootView.getContext();
    }

    private void initializeData() {
        OptionListItem optionListItem1 = new OptionListItem(context, context.getString(R.string.notification_settings_screen_title), new ClassForNotificationSettingsActivity(this));
        OptionListItem optionListItem2 = new OptionListItem(context, context.getString(R.string.login_logout_screen_title), new ClassForLogInSignUp(this));
        OptionListItem optionListItem3 = new OptionListItem(context, context.getString(R.string.about_screen_title), new ClassForAboutActivity(this));

        linearLayout.addView(optionListItem1.view, new LayoutParams(LayoutParams.MATCH_PARENT, 150));
        linearLayout.addView(optionListItem2.view, new LayoutParams(LayoutParams.MATCH_PARENT, 150));
        linearLayout.addView(optionListItem3.view, new LayoutParams(LayoutParams.MATCH_PARENT, 150));
    }
}