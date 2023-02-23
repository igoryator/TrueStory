package com.example.truestory.models;

import com.squareup.moshi.Json;

public class ClusterItemNewsItem {
    @Json(name = "created_at")
    private final String createdAt;
    public ClusterItemNewsItem(String s) {

        super();
        this.createdAt = s;
    }
    public final String getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public int hashCode() {
        return this.createdAt.hashCode();
    }

}
