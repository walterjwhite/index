package com.walterjwhite.index.api.model.query.predicate;

import com.walterjwhite.index.api.model.query.FieldType;
import com.walterjwhite.index.api.model.query.MatchType;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
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
}
