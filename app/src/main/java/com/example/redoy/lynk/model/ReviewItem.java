package com.example.redoy.lynk.model;

public class ReviewItem {

    private String name;
    private String timestamp;
    private String description;

    public ReviewItem(String name, String timestamp, String description) {
        this.name = name;
        this.timestamp = timestamp;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
