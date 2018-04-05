package com.example.redoy.lynk.application;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ApiClient {

    public static final String SERVER_URL = "http://bd.justlynk.com/api/";
    static final String SERVER_URL_THANA_AND_CATEGORY = "http://bd.justlynk.com/select/directory/";

    private static Retrofit retrofit = null;

    public static Retrofit getLynkClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getLynkClientForThanaAndCategory() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL_THANA_AND_CATEGORY)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
