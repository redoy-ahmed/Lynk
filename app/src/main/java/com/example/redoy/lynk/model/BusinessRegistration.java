package com.example.redoy.lynk.model;

public class BusinessRegistration {

    private String business_name;
    private String address;
    private String category;
    private String thana;
    private String verified_mobile;
    private String deal_title;

    public BusinessRegistration(String business_name, String address, String category, String thana, String verified_mobile, String deal_title) {
        this.business_name = business_name;
        this.address = address;
        this.category = category;
        this.thana = thana;
        this.verified_mobile = verified_mobile;
        this.deal_title = deal_title;
    }
}
