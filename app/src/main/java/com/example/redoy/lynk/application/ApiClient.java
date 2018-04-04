package com.example.redoy.lynk.application;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ApiClient {

    public static final String SERVER_URL = "http://bd.justlynk.com/api/";
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
}
