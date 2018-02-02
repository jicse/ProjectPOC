package com.balaji.projectpoc.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class provides the singleton retrofit object for network calls.
 */
public class ApiClient {

    public static final String BASE_URL = " https://dl.dropboxusercontent.com/";
    private static Retrofit retrofit = null;

    /**
     * Method to return Retrofit singleton object.
     *
     * @return Retrofit object.
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
