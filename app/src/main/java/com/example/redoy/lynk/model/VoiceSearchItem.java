package com.example.redoy.lynk.model;

/**
 * Created by redoy.ahmed on 11-Feb-2018.
 */

public class VoiceSearchItem {
    private String name;
    private int photo;
    private String color;

    public VoiceSearchItem(String name, int photo, String color) {
        this.name = name;
        this.photo = photo;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
