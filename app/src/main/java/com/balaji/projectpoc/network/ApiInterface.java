package com.balaji.projectpoc.network;

import com.balaji.projectpoc.model.Country;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by balaji on 31/1/18.
 */

public interface ApiInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<Country> getCountryData();

}
