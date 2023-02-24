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

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class StoryLoader {
    public StoryLoader(){

    }
    int lastOffset = 0;
    public List<StoryItem> loadStories(int limit){
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
                    // load content
                    Call<StoryDetailsResponse> detailsRequest = api.loadStoryBy(itm.getCluster_id(),"1.0.3", 5, "android");
                    Response<StoryDetailsResponse> detailsResponce = detailsRequest.execute();
                    StoryDetailsResponse detalles = detailsResponce.body();
                    if(detalles != null){
                        StringBuilder content = new StringBuilder();
                        List<ClusterSummary> summaries =  detalles.getContent().getData().getStoryDetails().get(0).getCluster().getClusterSummaries();
                        for (ClusterSummary sum:summaries
                             ) {
                            content.append(sum.getText()).append("\n");
                        }
                        itm.setContent(content.toString());
                    }




                    Stories.add(itm);
                }
                // load pictures

                for (StoryItem itm:Stories
                ) {
                    Call<ResponseBody> pictureReq = api.loadPictureBy(itm.getCluster_id());
                    Response<ResponseBody> response = pictureReq.execute();
                    if (response.isSuccessful()) {
                        ResponseBody body = response.body();
                        if (body != null) {
                            // display the image data in a ImageView or save it
                            Bitmap bm = BitmapFactory.decodeStream(body.byteStream());
                            itm.setPicture(bm);
                        }
                    }
                }



            }

        } catch (IOException e) {
            lastOffset += Stories.size();
            return Stories;
        }
        lastOffset += Stories.size();
        return Stories;
    }
}
