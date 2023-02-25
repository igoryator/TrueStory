package com.example.truestory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Message;

import com.example.truestory.models.ClusterSummary;
import com.example.truestory.models.StoryDetailsResponse;

import java.io.IOException;
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

public class StoryItem {
    private String cluster_id;
    private String header;
    private String content;
    private Bitmap picture;
    boolean isGetFullText;
    Handler updHandler = null;

    public StoryItem(String cluster_id){
        this.cluster_id = cluster_id;
        this.picture = null;
        this.header = "";
        this.content = "";
        this.isGetFullText = false;
    }

    public String getCluster_id() {
        return cluster_id;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public String getContent() {
        return content;
    }

    public String getHeader() {
        if(isGetFullText){
            if(!content.isEmpty()){
                return content;
            } else {
                return header;
            }
        }
        return header;
    }

    public boolean isGetFullText() {
        return isGetFullText;
    }

    public void setGetFullText(boolean getFullText) {
        isGetFullText = getFullText;
    }

    public void setCluster_id(String cluster_id) {
        this.cluster_id = cluster_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public void setUpdHandler(Handler updHandler) {
        this.updHandler = updHandler;
    }

    public void loadExtendInfo(){

        StoryItem itm = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient0 = new OkHttpClient().newBuilder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
                StoryApi api =  (StoryApi) new Retrofit.Builder().baseUrl("https://thetruestory.news/").client(okHttpClient0).addConverterFactory(((Converter.Factory) MoshiConverterFactory.create())).build().create(StoryApi.class);
                Call<StoryDetailsResponse> detailsRequest = api.loadStoryBy(itm.getCluster_id(),"1.0.3", 5, "android");
                Response<StoryDetailsResponse> detailsResponce = null;
                try {
                    detailsResponce = detailsRequest.execute();

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

                    if(updHandler != null){
                        // send update message
                        Message update = new Message();
                        update.what = 1;
                        Bundle idBundle = new Bundle();
                        idBundle.putString("id", itm.getCluster_id());
                        update.setData(idBundle);
                        updHandler.sendMessage(update);
                    }
                } catch (IOException e) {
                    return;
                }

            }
        }).start();



    }
}
