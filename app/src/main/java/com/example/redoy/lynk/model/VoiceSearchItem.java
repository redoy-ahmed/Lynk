package com.example.redoy.lynk.model;

/**
 * Created by redoy.ahmed on 11-Feb-2018.
 */

public class VoiceSearchItem {

    private String id;
    private String title;
    private String feature_img;
    private String thana;

    public VoiceSearchItem(String id, String title, String feature_img, String thana) {
        this.id = id;
        this.title = title;
        this.feature_img = feature_img;
        this.thana = thana;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFeature_img() {
        return feature_img;
    }

    public String getThana() {
        return thana;
    }
}
