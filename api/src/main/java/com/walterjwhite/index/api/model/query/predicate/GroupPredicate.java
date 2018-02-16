package com.walterjwhite.index.api.model.query.predicate;

import com.walterjwhite.index.api.model.query.Conjunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class GroupPredicate extends Predicate {
  @Column(nullable = false, updatable = false)
  protected Conjunction conjunction = Conjunction.And;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable
  protected List<Predicate> childrenPredicates = new ArrayList<>();

  public GroupPredicate(boolean invert, Conjunction conjunction) {
    super(invert);

    this.conjunction = conjunction;
  }

  public GroupPredicate() {
    super();
  }

  public Conjunction getConjunction() {
    return conjunction;
  }

  public void setConjunction(Conjunction conjunction) {
    this.conjunction = conjunction;
  }

  public List<Predicate> getChildrenPredicates() {
    return childrenPredicates;
  }

  public void setChildrenPredicates(List<Predicate> childrenPredicates) {
    this.childrenPredicates.clear();
    this.childrenPredicates.addAll(childrenPredicates);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    GroupPredicate that = (GroupPredicate) o;
    return conjunction == that.conjunction
        && Objects.equals(childrenPredicates, that.childrenPredicates);
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), conjunction, childrenPredicates);
  }
}
