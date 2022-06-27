package com.varrel.samsungb2b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvUser;
    public static AdapterRecyclerViewMain adapterUser;
    DataAPI dataApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataApi = new DataAPI();

        swipeRefreshLayout = findViewById(R.id.main_swiperefreshlayout);
        rvUser = findViewById(R.id.main_recyclerview);

        dataApi.getUserData(this);

        adapterUser = new AdapterRecyclerViewMain();
        rvUser.setAdapter(adapterUser);
        rvUser.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                refresh();
            }
        });
    }

    public void refresh(){
        Data.Data_User.clear();
        dataApi = new DataAPI();
        dataApi.getUserData(this);
    }



}