package com.example.truestory.models;

public class GenericSource {
    private final String slug;
    private final String title;
    public GenericSource(String s, String s1) {

        super();
        this.slug = s;
        this.title = s1;
    }
    public final String getSlug() {
        return this.slug;
    }

    public final String getTitle() {
        return this.title;
    }
}
