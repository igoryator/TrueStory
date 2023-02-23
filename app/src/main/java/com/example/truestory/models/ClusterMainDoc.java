package com.example.truestory.models;

import com.squareup.moshi.Json;

public class ClusterMainDoc {
    private final int count;
    @Json(name = "source_name")
    private final String sourceName;
    private final String timestamp;
    private final String title;
    private final String url;
    public ClusterMainDoc(String s, String s1, String s2, int v, String s3) {

        super();
        this.timestamp = s;
        this.title = s1;
        this.url = s2;
        this.count = v;
        this.sourceName = s3;
    }
    public final int getCount() {
        return this.count;
    }

    public final String getSourceName() {
        return this.sourceName;
    }

    public final String getTimestamp() {
        return this.timestamp;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getUrl() {
        return this.url;
    }
}
