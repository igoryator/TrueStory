package com.example.truestory.models;

import com.squareup.moshi.Json;

public class StoryDetails {
    @Json(name = "cluster")
    private final StoryDetailsCluster cluster;
    @Json(name = "cluster_id")
    private final String id;
    private final StoryDetailsIssue issue;
    public StoryDetails(String s, StoryDetailsIssue storyDetailsIssue0, StoryDetailsCluster storyDetailsCluster0) {

        super();
        this.id = s;
        this.issue = storyDetailsIssue0;
        this.cluster = storyDetailsCluster0;
    }
    public final StoryDetailsCluster getCluster() {
        return this.cluster;
    }

    public final String getId() {
        return this.id;
    }

    public final StoryDetailsIssue getIssue() {
        return this.issue;
    }
}
