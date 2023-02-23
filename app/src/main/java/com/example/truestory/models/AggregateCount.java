package com.example.truestory.models;

public class AggregateCount {
    private final int count;
    public AggregateCount(int v) {
        this.count = v;
    }
    public final int getCount() {
        return this.count;
    }
}
