package com.example.truestory.models;

import com.example.truestory.Story;
import com.squareup.moshi.Json;

import java.util.List;

public final class ContentData {
    @Json(name = "cluster_title_aggregate")
    private final ClusterTitleAggregate clusterTitleAggregate;
    @Json(name = "issue_aggregate")
    private final IssueAggregate issueAggregate;
    @Json(name = "cluster_rank")
    private final List<Story> stories;

    public ContentData(ClusterTitleAggregate clusterTitleAggregate0, IssueAggregate issueAggregate0, List list0) {

        super();
        this.clusterTitleAggregate = clusterTitleAggregate0;
        this.issueAggregate = issueAggregate0;
        this.stories = list0;

    }
    public final ClusterTitleAggregate getClusterTitleAggregate() {
        return this.clusterTitleAggregate;
    }

    public final IssueAggregate getIssueAggregate() {
        return this.issueAggregate;
    }

    public final List<Story> getStories() {
        return this.stories;
    }

    @Override
    public int hashCode() {
        return (this.clusterTitleAggregate.hashCode() * 0x1F + this.issueAggregate.hashCode()) * 0x1F + this.stories.hashCode();
    }

    @Override
    public String toString() {
        return "ContentData(clusterTitleAggregate=" + this.clusterTitleAggregate + ", issueAggregate=" + this.issueAggregate + ", stories=" + this.stories + ')';
    }
}
