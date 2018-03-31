package com.example.redoy.lynk.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.redoy.lynk.util.Constants;

public class CustomSharedPreference {
    private SharedPreferences sharedPref;

    public CustomSharedPreference(Context context) {
        sharedPref = context.getSharedPreferences(Constants.SHARED_PREF, 0);
    }

    public SharedPreferences getInstanceOfSharedPreference() {
        return sharedPref;
    }

    public void setUserData(String userData) {
        sharedPref.edit().putString(Constants.USER_DATA, userData).apply();
    }

    public String getUserData() {
        return sharedPref.getString(Constants.USER_DATA, "");
    }

    public void saveNotification(boolean notification) {
        sharedPref.edit().putBoolean(Constants.NOTIFICATION, notification).apply();
    }

    public boolean getSavedNotification() {
        return sharedPref.getBoolean(Constants.NOTIFICATION, false);
    }

    public void setCheckQuiz(String quiz) {
        sharedPref.edit().putString(Constants.CHECK_QUIZ, quiz).apply();
    }

    public void saveIsFirstTimeOpening(boolean isFirstTime) {
        sharedPref.edit().putBoolean(Constants.FIRST_TIME_OPENING, isFirstTime).apply();
    }

    public boolean getSavedIsFirstTimeOpening() {
        return sharedPref.getBoolean(Constants.FIRST_TIME_OPENING, true);
    }

    public void saveLanguage(String language) {
        sharedPref.edit().putString(Constants.LANGUAGE, language).apply();
    }

    public String getSavedLanguage() {
        return sharedPref.getString(Constants.LANGUAGE, "English");
    }
}
