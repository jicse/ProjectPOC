package com.balaji.projectpoc.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.balaji.projectpoc.R;
import com.balaji.projectpoc.adapter.CountryAdapter;
import com.balaji.projectpoc.model.Country;
import com.balaji.projectpoc.model.Row;
import com.balaji.projectpoc.network.AppRequest;
import com.balaji.projectpoc.utils.AppUtils;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AppRequest.AppRequestCallBack {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    TextView errorText;
    AppRequest appRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        errorText = findViewById(R.id.error_text);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setRefreshing(true);
        appRequest = new AppRequest(this, this);
        appRequest.fetchDataForCountry();
    }

    @Override
    public void onSuccess(Response<Country> response) {
        swipeRefresh.setRefreshing(false);
        setTitle(response.body().getTitle());
        CountryAdapter adapter = new CountryAdapter(getApplicationContext(), AppUtils.removeEmptyData(response.body()), new CountryAdapter.OnItemClickListener() {
            @Override
            public void onClick(Row row) {
                Toast.makeText(MainActivity.this, getString(R.string.on_click), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(String networkError) {
        swipeRefresh.setRefreshing(false);
        errorText.setText(AppUtils.formNetworkErrorText(networkError));
    }

    @Override
    public void onRefresh() {
        appRequest.fetchDataForCountry();
    }

    /**
     * Method to set title for the action bar.
     *
     * @param title
     */
    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

}
