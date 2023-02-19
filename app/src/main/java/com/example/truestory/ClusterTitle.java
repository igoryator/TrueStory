package com.example.truestory;

import com.squareup.moshi.Json;

import java.util.List;

public final class ClusterTitle {

    @Json(name = "title")
    private final String title;
    @Json(name = "cluster_title_source")
    private final ClusterTitleSource clusterTitleSource;
    public ClusterTitle(String title0, ClusterTitleSource clusterTitleSource0) {

        super();
        this.title = title0;
        this.clusterTitleSource = clusterTitleSource0;

    }
    public String getTitle(){
        return title;
    }

    public ClusterTitleSource getClusterTitleSource() {
        return clusterTitleSource;
    }
}
