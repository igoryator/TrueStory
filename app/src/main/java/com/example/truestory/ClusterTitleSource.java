package com.example.truestory;

import com.squareup.moshi.Json;

public final class ClusterTitleSource {
    @Json(name = "source_date")
    private final String sourceDate;
    public ClusterTitleSource(String sourceDate0) {

        super();
        this.sourceDate = sourceDate0;

    }

    public String getSourceDate() {
        return sourceDate;
    }
}
