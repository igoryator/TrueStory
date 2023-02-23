package com.example.truestory.models;

import com.squareup.moshi.Json;

import java.util.List;

public class StoryDetailsContentData {
    @Json(name = "cluster_rank")
    private final List<StoryDetails> storyDetails;
    public StoryDetailsContentData(List<StoryDetails> list0) {

        super();
        this.storyDetails = list0;
    }
    public final List<StoryDetails> getStoryDetails() {
        return this.storyDetails;
    }


}
