package com.example.truestory.models;

public final class IssueAggregate {
    private final IssueAggregateMax aggregate;
    public IssueAggregate(IssueAggregateMax issueAggregateMax0) {

        super();
        this.aggregate = issueAggregateMax0;
    }
    public final IssueAggregateMax getAggregate() {
        return this.aggregate;
    }

    @Override
    public int hashCode() {
        return this.aggregate.hashCode();
    }

    @Override
    public String toString() {
        return "IssueAggregate(aggregate=" + this.aggregate + ')';
    }
}
