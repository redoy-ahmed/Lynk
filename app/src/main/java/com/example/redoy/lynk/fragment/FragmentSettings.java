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

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.activity.IntroductionActivity;
import com.example.redoy.lynk.activity.NotificationSettings;
import com.example.redoy.lynk.widget.OptionListItem;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

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
            fragmentSettings.startActivity(new Intent(fragmentSettings.getActivity(), IntroductionActivity.class));
        }
    }

    class ClassForRateTheApp implements View.OnClickListener {
        final FragmentSettings fragmentSettings;

        ClassForRateTheApp(FragmentSettings fragmentSettings) {
            this.fragmentSettings = fragmentSettings;
        }

        public void onClick(View view) {
            //fragmentSettings.startActivity(new Intent(fragmentSettings.getActivity(), IntroductionActivity.class));
        }
    }

    class ClassForAboutActivity implements View.OnClickListener {
        final FragmentSettings fragmentSettings;

        ClassForAboutActivity(FragmentSettings fragmentSettings) {
            this.fragmentSettings = fragmentSettings;
        }

        public void onClick(View view) {
            new SweetAlertDialog(rootView.getContext(), SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("About")
                    .setContentText("Having your business listed at our justlynk site will give your business or service exposure nationwide. Advertising with justlynk.com is simple, affordable and comprehensive to cover all your advertising needs. As a sales incentive, we provide our advertisers referral services, Lynk club membership Card, and more")
                    .setConfirmText("Ok")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                        }
                    })
                    .show();
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
        OptionListItem optionListItem1 = new OptionListItem(context, context.getString(R.string.notification_settings_screen_title), R.drawable.ic_notifications_black_24dp, new ClassForNotificationSettingsActivity(this));
        OptionListItem optionListItem2 = new OptionListItem(context, context.getString(R.string.log_in_screen_title), R.drawable.ic_person_black_24dp, new ClassForLogInSignUp(this));
        OptionListItem optionListItem3 = new OptionListItem(context, context.getString(R.string.rate_the_app_title), R.drawable.ic_plus_one_black_24dp, new ClassForRateTheApp(this));
        OptionListItem optionListItem4 = new OptionListItem(context, context.getString(R.string.about_screen_title), R.drawable.ic_info_black_24dp, new ClassForAboutActivity(this));

        linearLayout.addView(optionListItem1.view, new LayoutParams(LayoutParams.MATCH_PARENT, 150));
        linearLayout.addView(optionListItem2.view, new LayoutParams(LayoutParams.MATCH_PARENT, 150));
        linearLayout.addView(optionListItem3.view, new LayoutParams(LayoutParams.MATCH_PARENT, 150));
        linearLayout.addView(optionListItem4.view, new LayoutParams(LayoutParams.MATCH_PARENT, 150));
    }
}