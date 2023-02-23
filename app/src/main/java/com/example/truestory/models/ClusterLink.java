package com.example.truestory.models;

import com.squareup.moshi.Json;

public class ClusterLink {
    private final int count;
    @Json(name = "source_name")
    private final String sourceName;
    private final String text;
    private final String timestamp;
    private final String title;
    private final String url;
    public ClusterLink(String s, String s1, String s2, String s3, int v, String s4) {

        super();
        this.text = s;
        this.title = s1;
        this.sourceName = s2;
        this.url = s3;
        this.count = v;
        this.timestamp = s4;
    }
    public final int getCount() {
        return this.count;
    }

    public final String getSourceName() {
        return this.sourceName;
    }

    public final String getText() {
        return this.text;
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
