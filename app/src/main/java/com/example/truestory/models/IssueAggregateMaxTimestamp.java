package com.example.truestory.models;

public final class IssueAggregateMaxTimestamp {
    private final String timestamp;
    public IssueAggregateMaxTimestamp(String s) {
        super();
        this.timestamp = s;
    }
    public final String getTimestamp() {
        return this.timestamp;
    }

    @Override
    public int hashCode() {
        return this.timestamp.hashCode();
    }

    @Override
    public String toString() {
        return "IssueAggregateMaxTimestamp(timestamp=" + this.timestamp + ')';
    }
}
