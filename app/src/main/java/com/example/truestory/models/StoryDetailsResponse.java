package com.example.truestory.models;

public class StoryDetailsResponse {
    private final StoryDetailsContent content;
    public StoryDetailsResponse(StoryDetailsContent storyDetailsContent0) {

        super();
        this.content = storyDetailsContent0;
    }
    public final StoryDetailsContent getContent() {
        return this.content;
    }

    @Override
    public int hashCode() {
        return this.content.hashCode();
    }

    @Override
    public String toString() {
        return "StoryDetailsResponse(content=" + this.content + ')';
    }
}
