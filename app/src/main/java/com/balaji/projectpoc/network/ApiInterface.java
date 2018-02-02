package com.balaji.projectpoc.network;

import com.balaji.projectpoc.model.Country;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This interface is used by retrofit for network calls.
 */
public interface ApiInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<Country> getCountryData();

}
