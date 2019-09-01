package com.walterjwhite.index.api.model.query.predicate;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@Entity
public class Predicate extends AbstractEntity {
  @Column(nullable = false, updatable = false)
  protected boolean invert = false;

  //  @ManyToOne
  //  @JoinColumn
  //  protected Predicate parentPredicate;
}
