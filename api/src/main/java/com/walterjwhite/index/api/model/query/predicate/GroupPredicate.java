package com.walterjwhite.index.api.model.query.predicate;

import com.walterjwhite.index.api.model.query.Conjunction;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@NoArgsConstructor
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
}
