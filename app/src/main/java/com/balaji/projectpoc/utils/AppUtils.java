package com.balaji.projectpoc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.balaji.projectpoc.model.Country;
import com.balaji.projectpoc.model.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppUtils {

    /**
     * Method to remove the empty data of country.
     *
     * @param data Country object from api.
     * @return Country oject after empty filter.
     */
    public static Country removeEmptyData(Country data) {

        List<Row> oldRows = data.getRows();
        List<Row> newRows = new ArrayList<>();

        Iterator<Row> iterator = oldRows.iterator();

        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (!TextUtils.isEmpty(row.getTitle()) ||
                    !TextUtils.isEmpty(row.getTitle()) ||
                    !TextUtils.isEmpty(row.getTitle())) {
                newRows.add(row);
            }
        }

        data.setRows(newRows);
        return data;
    }

    /**
     * Method to check is network available.
     *
     * @param context Context from activity.
     * @return boolean for is connectivity.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    /**
     * Method to form network error text.
     *
     * @param errorMessage error message from request.
     * @return String complete error message.
     */
    public static String formNetworkErrorText(String errorMessage) {
        return new StringBuilder().append(errorMessage).append("\n Pull to Refresh").toString();
    }
}
