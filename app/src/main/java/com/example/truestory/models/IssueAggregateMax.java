package com.example.truestory.models;

public final class IssueAggregateMax {
    private final IssueAggregateMaxTimestamp max;
    public IssueAggregateMax(IssueAggregateMaxTimestamp issueAggregateMaxTimestamp0) {

        super();
        this.max = issueAggregateMaxTimestamp0;
    }
    public final IssueAggregateMaxTimestamp getMax() {
        return this.max;
    }

    @Override
    public int hashCode() {
        return this.max.hashCode();
    }

    @Override
    public String toString() {
        return "IssueAggregateMax(max=" + this.max + ')';
    }
}
