package com.example.truestory;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<StoryItem> rowsArrayList = new ArrayList<>();

    List<StoryItem> loadedStories = new ArrayList<>();
    boolean isWaitingForUpdate = false;
    boolean isLoading = false;

    HashMap<String, Integer> clusterIndexes = new HashMap<String, Integer>();
    private final  Handler updateHandler = new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg) {

            if(msg.what == 0){
                // main list updated
                if(isWaitingForUpdate){
                    isWaitingForUpdate = false;
                    if(rowsArrayList.get(rowsArrayList.size()-1)==null){
                        int removePos = rowsArrayList.size() - 1;
                        rowsArrayList.remove(removePos);
                        recyclerAdapter.notifyItemRemoved(removePos);
                    }

                    int insertPos = rowsArrayList.size();
                    int index = insertPos;
                    for (StoryItem itm:loadedStories
                         ) {
                        rowsArrayList.add(itm);
                        clusterIndexes.put(itm.getCluster_id(), index);
                        index++;
                    }
                    //rowsArrayList.addAll(loadedStories);
                    recyclerAdapter.notifyItemRangeInserted(insertPos, loadedStories.size());
                    loadedStories.clear();
                    isLoading = false;
                    loadNextPortion();

                }


                //recyclerAdapter.notifyDataSetChanged();
            } else if(msg.what == 1){
                Bundle data = msg.getData();
                String cluster = data.getString("id");
                if(clusterIndexes.containsKey(cluster)){
                    int position = clusterIndexes.getOrDefault(cluster, -1);
                    if(position != -1){
                        recyclerAdapter.notifyItemChanged(position);
                    }
                }



            }

        }
    };
    StoryLoader storyLoader = new StoryLoader(updateHandler);
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
        isWaitingForUpdate = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadedStories = storyLoader.loadStories(12, true);
                isLoading = false;
                updateHandler.sendEmptyMessage(0);
            }
        }).start();



    }
    public class itmClickListener implements ItemClickListener{
        @Override
        public void itemClicked(View v, int position){

            StoryItem itm = rowsArrayList.get(position);
            if(itm.isGetFullText()){
                itm.setGetFullText(false);
            } else {
                itm.setGetFullText(true);
            }
            recyclerAdapter.notifyItemChanged(position);
        }
    }
    itmClickListener click = new itmClickListener();
    private void initAdapter() {

        recyclerAdapter = new RecyclerAdapter(rowsArrayList, click);
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
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == rowsArrayList.size() - 1) {

                    //bottom of list!
                    loadMore();

                }

            }
        });


    }
    private void loadNextPortion(){
        if(isLoading){
            return;
        }
        isLoading = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadedStories = storyLoader.loadStories(12,true);
                isLoading = false;
                updateHandler.sendEmptyMessage(0);

            }
        }).start();

    }
    private void loadMore() {
        if(isLoading){
            // wait for new portion and draw loading
            if(rowsArrayList.get(rowsArrayList.size()-1)==null){
                return;
            }
            int scrollPosition = rowsArrayList.size();
            rowsArrayList.add(null);
            recyclerAdapter.notifyItemInserted(rowsArrayList.size() - 1);
            isWaitingForUpdate = true;

        } else{
            if(!loadedStories.isEmpty()){
                isWaitingForUpdate = true;
                updateHandler.sendEmptyMessage(0);

            } else {
                int scrollPosition = rowsArrayList.size();
                rowsArrayList.add(null);
                recyclerAdapter.notifyItemInserted(rowsArrayList.size() - 1);
                isWaitingForUpdate = true;
                loadNextPortion();
            }
        }

    }


}