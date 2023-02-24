package com.example.truestory.models;

public final class StoriesResponse {

    private final StoriesContent content;
    public StoriesResponse(StoriesContent storiesContent0) {
        super();
        this.content = storiesContent0;
    }
    public final StoriesContent getContent() {
        return this.content;
    }

    @Override
    public int hashCode() {
        return this.content.hashCode();
    }

    @Override
    public String toString() {
        return "StoriesResponse(content=" + this.content + ')';
    }
}
