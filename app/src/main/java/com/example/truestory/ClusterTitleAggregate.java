package com.example.truestory;

public final class ClusterTitleAggregate {
    private final TitleAggregate aggregate;
    public ClusterTitleAggregate(TitleAggregate titleAggregate0) {

        super();
        this.aggregate = titleAggregate0;
    }
    public final TitleAggregate getAggregate() {
        return this.aggregate;
    }

    @Override
    public int hashCode() {
        return this.aggregate.hashCode();
    }

    @Override
    public String toString() {
        return "ClusterTitleAggregate(aggregate=" + this.aggregate + ')';
    }
}
