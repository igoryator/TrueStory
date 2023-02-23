package com.example.truestory.models;

import com.squareup.moshi.Json;

public class ClusterSummary {
    @Json(name = "summary_source")
    private final ClusterSource summarySource;
    @Json(name = "text")
    private final String text;
    public ClusterSummary(String s, ClusterSource clusterSource0) {

        super();
        this.text = s;
        this.summarySource = clusterSource0;
    }
    public final ClusterSource getSummarySource() {
        return this.summarySource;
    }

    public final String getText() {
        return this.text;
    }

}
