package com.example.truestory.models;

import com.squareup.moshi.Json;

public class ClusterItem {
    @Json(name = "news_newsitem")
    private final ClusterItemNewsItem newsItem;
    private final int rank;
    public ClusterItem(int v, ClusterItemNewsItem clusterItemNewsItem0) {

        super();
        this.rank = v;
        this.newsItem = clusterItemNewsItem0;

    }
    public final ClusterItemNewsItem getNewsItem() {
        return this.newsItem;
    }

    public final int getRank() {
        return this.rank;
    }
}
