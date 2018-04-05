package com.example.redoy.lynk.model;

public class BusinessRegistration {

    private String name;
    private String password;
    private String password_confirmation;
    private String email;
    private String verified_mobile;
    private String business_name;
    private String business_description;
    private String address;
    private String category;
    private String thana;
    private String city;
    private String zip_code;
    private String country;
    private String google_location;
    private String lat;
    private String lng;
    private String deal_title;

    public BusinessRegistration(String name, String password, String password_confirmation, String email, String verified_mobile, String business_name, String business_description, String address, String category, String thana, String city, String zip_code, String country, String google_location, String lat, String lng, String deal_title) {
        this.name = name;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.email = email;
        this.verified_mobile = verified_mobile;
        this.business_name = business_name;
        this.business_description = business_description;
        this.address = address;
        this.category = category;
        this.thana = thana;
        this.city = city;
        this.zip_code = zip_code;
        this.country = country;
        this.google_location = google_location;
        this.lat = lat;
        this.lng = lng;
        this.deal_title = deal_title;
    }
}
