package com.example.truestory;

import com.example.truestory.models.Cluster;
import com.squareup.moshi.Json;

public final class Story {
    @Json(name = "cluster_id")
    private final String cluster_id;
    @Json(name = "rank")
    private final Double rank;
    @Json(name = "cluster")
    private final Cluster cluster;
    public Story(String cluster_id0, Double rank0, Cluster cluster0) {

        super();
        this.cluster_id = cluster_id0;
        this.rank = rank0;
        this.cluster = cluster0;

    }

    public String getCluster_id(){
        return cluster_id;
    }
    public Cluster getCluster(){
        return cluster;
    }
}
