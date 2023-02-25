package com.example.truestory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.truestory.models.ClusterSummary;
import com.example.truestory.models.StoriesResponse;
import com.example.truestory.models.Story;
import com.example.truestory.models.StoryDetailsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class StoryLoader {
    Handler updHandler;
    public StoryLoader(Handler updHandler){
        this.updHandler = updHandler;
    }
    int lastOffset = 0;
    public List<StoryItem> loadStories(int limit, boolean loadExtend){
        OkHttpClient okHttpClient0 = new OkHttpClient().newBuilder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
        StoryApi api =  (StoryApi) new Retrofit.Builder().baseUrl("https://thetruestory.news/").client(okHttpClient0).addConverterFactory(((Converter.Factory) MoshiConverterFactory.create())).build().create(StoryApi.class);
        Call<StoriesResponse> storiesReq = api.loadStories("ru", limit, lastOffset, "1.0.3", 5, "android");
        Response<StoriesResponse> resp;
        StoriesResponse stories;
        List<Story> clusters;
        List<StoryItem> Stories = new ArrayList<StoryItem>();
        try {
            resp = storiesReq.execute();
            stories = resp.body();
            if(stories != null){
                clusters = stories.getContent().getData().getStories();
                for (Story item:clusters
                ) {
                    StoryItem itm  = new StoryItem(item.getCluster_id());
                    itm.setHeader(item.getCluster().getTitles().get(0).getTitle());
                    if(loadExtend){
                        // load content
                        itm.setUpdHandler(updHandler);
                        itm.loadExtendInfo();
                    }

                    Stories.add(itm);
                }


            }

        } catch (IOException e) {
            lastOffset += Stories.size();
            return Stories;
        }
        lastOffset += Stories.size();
        return Stories;
    }
    public void loadPicture(StoryItem story){
        OkHttpClient okHttpClient0 = new OkHttpClient().newBuilder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
        StoryApi api =  (StoryApi) new Retrofit.Builder().baseUrl("https://thetruestory.news/").client(okHttpClient0).addConverterFactory(((Converter.Factory) MoshiConverterFactory.create())).build().create(StoryApi.class);
        Call<ResponseBody> pictureReq = api.loadPictureBy(story.getCluster_id());
        Response<ResponseBody> response = null;
        try {
            response = pictureReq.execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    // display the image data in a ImageView or save it
                    Bitmap bm = BitmapFactory.decodeStream(body.byteStream());
                    story.setPicture(bm);
                }
            }
        } catch (IOException e) {
            return;
        }


    }
    public Bitmap loadPicture(String cluster_id){
        OkHttpClient okHttpClient0 = new OkHttpClient().newBuilder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
        StoryApi api =  (StoryApi) new Retrofit.Builder().baseUrl("https://thetruestory.news/").client(okHttpClient0).addConverterFactory(((Converter.Factory) MoshiConverterFactory.create())).build().create(StoryApi.class);
        Call<ResponseBody> pictureReq = api.loadPictureBy(cluster_id);
        Response<ResponseBody> response = null;
        try {
            response = pictureReq.execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    // display the image data in a ImageView or save it
                    Bitmap bm = BitmapFactory.decodeStream(body.byteStream());
                    return bm;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;

    }

}
