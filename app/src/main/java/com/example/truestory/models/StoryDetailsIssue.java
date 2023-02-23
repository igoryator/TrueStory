package com.example.truestory.models;

public class StoryDetailsIssue {
    private final String id;
    private final String timestamp;
    public StoryDetailsIssue(String s, String s1) {
        super();
        this.id = s;
        this.timestamp = s1;
    }
    public final String getId() {
        return this.id;
    }

    public final String getTimestamp() {
        return this.timestamp;
    }
}
