package com.walterjwhite.index.api.model.query.predicate;

import com.walterjwhite.index.api.model.query.FieldType;
import com.walterjwhite.index.api.model.query.MatchType;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class AttributePredicate extends Predicate {
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false)
  protected FieldType fieldType;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false)
  protected MatchType matchType;

  @Column(nullable = false, updatable = false)
  protected String attributePath;

  @Lob
  @Column(nullable = false, updatable = false)
  protected Serializable argument;

  public AttributePredicate(
      boolean invert,
      FieldType fieldType,
      MatchType matchType,
      String attributePath,
      Serializable argument) {
    super(invert);
    this.fieldType = fieldType;
    this.matchType = matchType;
    this.attributePath = attributePath;
    this.argument = argument;
  }

  public AttributePredicate(
      FieldType fieldType, MatchType matchType, String attributePath, Serializable argument) {
    super();
    this.fieldType = fieldType;
    this.matchType = matchType;
    this.attributePath = attributePath;
    this.argument = argument;
  }

  public AttributePredicate() {
    super();
  }

  public FieldType getFieldType() {
    return fieldType;
  }

  public void setFieldType(FieldType fieldType) {
    this.fieldType = fieldType;
  }

  public MatchType getMatchType() {
    return matchType;
  }

  public void setMatchType(MatchType matchType) {
    this.matchType = matchType;
  }

  public String getAttributePath() {
    return attributePath;
  }

  public void setAttributePath(String attributePath) {
    this.attributePath = attributePath;
  }

  public Serializable getArgument() {
    return argument;
  }

  public void setArgument(Serializable argument) {
    this.argument = argument;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    AttributePredicate that = (AttributePredicate) o;
    return fieldType == that.fieldType
        && matchType == that.matchType
        && Objects.equals(attributePath, that.attributePath)
        && Objects.equals(argument, that.argument);
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), fieldType, matchType, attributePath, argument);
  }
}
