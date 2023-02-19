package com.example.truestory;

public final class TitleAggregate {

    private final int count;
    public TitleAggregate(int v) {
        this.count = v;
    }
    public final int getCount() {
        return this.count;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.count);
    }

    @Override
    public String toString() {
        return "TitleAggregate(count=" + this.count + ')';
    }
}
