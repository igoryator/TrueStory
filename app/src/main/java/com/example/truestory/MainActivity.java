package com.example.truestory;

import android.app.Activity;
import android.os.Bundle;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<StoryItem> rowsArrayList = new ArrayList<>();

    boolean isLoading = false;
    private final  Handler updateHandler = new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg) {
            recyclerAdapter.notifyDataSetChanged();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        initAdapter();
        populateData();

        initScrollListener();
        OkHttpClient okHttpClient0 = new OkHttpClient().newBuilder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();

    }

    private void populateData() {

        rowsArrayList.add(null);
        recyclerAdapter.notifyItemInserted(rowsArrayList.size() - 1);
        isLoading = true;
        final List<String> firstStories;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<StoryItem> firstStories = StoryLoader.loadStories(10,0);
                rowsArrayList.remove(rowsArrayList.size() - 1);
                rowsArrayList.addAll(firstStories);

                isLoading = false;
                updateHandler.sendEmptyMessage(0);
            }
        }).start();



    }

    private void initAdapter() {

        recyclerAdapter = new RecyclerAdapter(rowsArrayList);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == rowsArrayList.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });


    }

    private void loadMore() {
        int scrollPosition = rowsArrayList.size();
        rowsArrayList.add(null);
        recyclerAdapter.notifyItemInserted(rowsArrayList.size() - 1);

        final List<String> firstStories;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<StoryItem> firstStories = StoryLoader.loadStories(10,scrollPosition);
                rowsArrayList.remove(rowsArrayList.size() - 1);
                rowsArrayList.addAll(firstStories);
                isLoading = false;
                updateHandler.sendEmptyMessage(0);
            }
        }).start();





    }


}