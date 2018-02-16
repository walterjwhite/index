package com.walterjwhite.index.api.model.query.predicate;

public class Range {
  protected Number low;
  protected Number high;

  public Range(Number low, Number high) {
    super();

    this.low = low;
    this.high = high;
  }

  public Range() {
    super();
  }

  public Number getLow() {
    return low;
  }

  public void setLow(Number low) {
    this.low = low;
  }

  public Number getHigh() {
    return high;
  }

  public void setHigh(Number high) {
    this.high = high;
  }
}
