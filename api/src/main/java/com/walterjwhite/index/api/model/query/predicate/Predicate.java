package com.walterjwhite.index.api.model.query.predicate;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.util.Objects;
import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Predicate extends AbstractEntity {
  @Column(nullable = false, updatable = false)
  protected boolean invert = false;

  //  @ManyToOne
  //  @JoinColumn
  //  protected Predicate parentPredicate;

  public Predicate(boolean invert) {
    super();
    this.invert = invert;
  }

  public Predicate() {
    super();
  }

  public boolean isInvert() {
    return invert;
  }

  public void setInvert(boolean invert) {
    this.invert = invert;
  }

  //  public Predicate getParentPredicate() {
  //    return parentPredicate;
  //  }
  //
  //  public void setParentPredicate(Predicate parentPredicate) {
  //    this.parentPredicate = parentPredicate;
  //  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Predicate predicate = (Predicate) o;
    return invert == predicate.invert;
  }

  @Override
  public int hashCode() {

    return Objects.hash(invert);
  }
}
