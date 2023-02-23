package com.example.truestory;

import android.graphics.Bitmap;
import android.media.Image;

public class StoryItem {
    private String cluster_id;
    private String header;
    private String content;
    private Bitmap picture;
    public StoryItem(String cluster_id){
        this.cluster_id = cluster_id;
        this.picture = null;
        this.header = "";
        this.content = "";
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
        return header;
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
}
