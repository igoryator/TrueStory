package com.example.truestory;

import android.app.Activity;
import android.os.Bundle;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Converter.Factory;
import retrofit2.Response;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<String> rowsArrayList = new ArrayList<>();

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
    private List<String> getStories(int limit, int offset){
        OkHttpClient okHttpClient0 = new OkHttpClient().newBuilder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
        StoryApi api =  (StoryApi) new Builder().baseUrl("https://thetruestory.news/api/").client(okHttpClient0).addConverterFactory(((Factory)MoshiConverterFactory.create())).build().create(StoryApi.class);
        Call<StoriesResponse> storiesReq = api.loadStories("ru", limit, offset, "1.0.3", 5, "android");
        Response<StoriesResponse> resp;
        StoriesResponse stories;
        List<Story> clusters;
        List<String> Headers = new ArrayList<String>();
        try {
            resp = storiesReq.execute();
            stories = resp.body();
            clusters = stories.getContent().getData().getStories();
            for (Story item:clusters
                 ) {
                Headers.add(item.getCluster().getTitles().get(0).getTitle());
            }
        } catch (IOException e) {
            return new ArrayList<String>();
        }
        return Headers;
    }
    private void populateData() {

        rowsArrayList.add(null);
        recyclerAdapter.notifyItemInserted(rowsArrayList.size() - 1);
        isLoading = true;
        final List<String> firstStories;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> firstStories = getStories(10,0);
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
                List<String> firstStories = getStories(10,scrollPosition);
                rowsArrayList.remove(rowsArrayList.size() - 1);
                rowsArrayList.addAll(firstStories);
                isLoading = false;
                updateHandler.sendEmptyMessage(0);
            }
        }).start();





    }


}