package com.example.truestory.models;

public class StoryDetailsContent {
    private final StoryDetailsContentData data;
    public StoryDetailsContent(StoryDetailsContentData storyDetailsContentData0) {
        super();
        this.data = storyDetailsContentData0;
    }
    public final StoryDetailsContentData getData() {
        return this.data;
    }

    @Override
    public int hashCode() {
        return this.data.hashCode();
    }

    @Override
    public String toString() {
        return "StoryDetailsContent(data=" + this.data + ')';
    }
}
