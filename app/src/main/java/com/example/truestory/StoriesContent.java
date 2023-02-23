package com.example.truestory;

import com.example.truestory.models.ContentData;

public final class StoriesContent {
    private final ContentData data;
    public StoriesContent(ContentData contentData0) {
        super();
        this.data = contentData0;
    }
    public final ContentData getData() {
        return this.data;
    }

    @Override
    public int hashCode() {
        return this.data.hashCode();
    }

    @Override
    public String toString() {
        return "StoriesContent(data=" + this.data + ')';
    }
}
