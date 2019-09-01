package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.model.query.SearchQuery;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IndexRecord extends AbstractEntity {

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected SearchQuery searchQuery;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Index index;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected EntityType entityType;

  @Column(nullable = false, updatable = false)
  protected String entityId;

  @EqualsAndHashCode.Exclude
  @Column(nullable = false, updatable = false)
  protected double score;
}
