package com.walterjwhite.index.api.model.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SearchQueryEntityType extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected EntityType entityType;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected SearchQuery searchQuery;
}
