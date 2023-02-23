package com.example.truestory.models;

import com.squareup.moshi.Json;

import java.util.List;

public final class Cluster {
    @Json(name = "cluster_titles")
    private final List<ClusterTitle> titles;
    public Cluster(List<ClusterTitle> titles0) {

        super();
        this.titles = titles0;

    }
    public List<ClusterTitle> getTitles(){
        return titles;
    }
}
