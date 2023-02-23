package com.example.truestory.models;

import com.squareup.moshi.Json;

public class ClusterSource {
    private final GenericSource source;
    @Json(name = "source_date")
    private final String sourceDate;
    private final String url;
    public ClusterSource(String s, String s1, GenericSource genericSource0) {

        super();
        this.url = s;
        this.sourceDate = s1;
        this.source = genericSource0;
    }
    public final GenericSource getSource() {
        return this.source;
    }

    public final String getSourceDate() {
        return this.sourceDate;
    }

    public final String getUrl() {
        return this.url;
    }
}
