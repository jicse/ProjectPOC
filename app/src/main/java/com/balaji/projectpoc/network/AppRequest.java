package com.balaji.projectpoc.network;

import android.content.Context;

import com.balaji.projectpoc.R;
import com.balaji.projectpoc.model.Country;
import com.balaji.projectpoc.utils.AppUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class provides the methods to fetch data from API.
 */
public class AppRequest {

    private Context mContext;
    private AppRequestCallBack mCallBack;

    public AppRequest(Context mContext, AppRequestCallBack mCallback) {
        this.mContext = mContext;
        this.mCallBack = mCallback;
    }

    /**
     * Method to fetch data for country.
     */
    public void fetchDataForCountry() {
        if (!AppUtils.isNetworkAvailable(mContext)) {
            mCallBack.onError(mContext.getString(R.string.no_network));
            return;
        }
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Country> call = apiService.getCountryData();
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                mCallBack.onSuccess(response);
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                mCallBack.onError(t.getMessage());
            }
        });
    }

    public interface AppRequestCallBack {
        void onSuccess(Response<Country> response);

        void onError(String networkError);
    }

}
