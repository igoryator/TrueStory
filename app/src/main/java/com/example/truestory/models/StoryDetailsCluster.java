package com.example.truestory.models;

import com.squareup.moshi.Json;

import java.util.List;

public class StoryDetailsCluster {
    @Json(name = "cluster_items")
    private final List<ClusterItem> clusterItems;
    @Json(name = "cluster_items_aggregate")
    private final AggregateField clusterItemsAggregate;
    @Json(name = "cluster_links")
    private final List<ClusterLink> clusterLinks;
    @Json(name = "cluster_links_aggregate")
    private final AggregateField clusterLinksAggregate;
    @Json(name = "cluster_main_docs")
    private final List<ClusterMainDoc> clusterMainDocs;
    @Json(name = "cluster_speeches")
    private final List<ClusterSpeech> clusterSpeeches;
    @Json(name = "cluster_speeches_aggregate")
    private final AggregateField clusterSpeechesAggregate;
    @Json(name = "cluster_summaries")
    private final List<ClusterSummary> clusterSummaries;
    @Json(name = "cluster_titles")
    private final List<ClusterTitle> clusterTitles;
    private final String timestamp;
    public StoryDetailsCluster(List list0, AggregateField aggregateField0, String s, List list1, List list2, AggregateField aggregateField1, List list3, List list4, List list5, AggregateField aggregateField2) {

        super();
        this.clusterTitles = list0;
        this.clusterItemsAggregate = aggregateField0;
        this.timestamp = s;
        this.clusterSummaries = list1;
        this.clusterSpeeches = list2;
        this.clusterSpeechesAggregate = aggregateField1;
        this.clusterMainDocs = list3;
        this.clusterItems = list4;
        this.clusterLinks = list5;
        this.clusterLinksAggregate = aggregateField2;
    }
    public final List getClusterItems() {
        return this.clusterItems;
    }

    public final AggregateField getClusterItemsAggregate() {
        return this.clusterItemsAggregate;
    }

    public final List<ClusterLink> getClusterLinks() {
        return this.clusterLinks;
    }

    public final AggregateField getClusterLinksAggregate() {
        return this.clusterLinksAggregate;
    }

    public final List<ClusterMainDoc> getClusterMainDocs() {
        return this.clusterMainDocs;
    }

    public final List<ClusterSpeech> getClusterSpeeches() {
        return this.clusterSpeeches;
    }

    public final AggregateField getClusterSpeechesAggregate() {
        return this.clusterSpeechesAggregate;
    }

    public final List<ClusterSummary> getClusterSummaries() {
        return this.clusterSummaries;
    }

    public final List<ClusterTitle> getClusterTitles() {
        return this.clusterTitles;
    }

    public final String getTimestamp() {
        return this.timestamp;
    }

}

