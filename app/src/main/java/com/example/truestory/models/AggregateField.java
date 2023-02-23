package com.example.truestory.models;

public class AggregateField {
    private final AggregateCount aggregate;
    public AggregateField(AggregateCount aggregateCount0) {

        super();
        this.aggregate = aggregateCount0;
    }
    public final AggregateCount getAggregate() {
        return this.aggregate;
    }
}
