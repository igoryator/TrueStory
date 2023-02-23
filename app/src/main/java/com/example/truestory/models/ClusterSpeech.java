package com.example.truestory.models;

import com.squareup.moshi.Json;

public class ClusterSpeech {
    private final String clause;
    @Json(name = "news_newsitem")
    private final ClusterSource newsItem;
    private final String speech;
    public ClusterSpeech(String s, String s1, ClusterSource clusterSource0) {

        super();
        this.speech = s;
        this.clause = s1;
        this.newsItem = clusterSource0;
    }
    public final String getClause() {
        return this.clause;
    }

    public final ClusterSource getNewsItem() {
        return this.newsItem;
    }

    public final String getSpeech() {
        return this.speech;
    }
}
