package com.walterjwhite.index.api.model.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SearchQueryEntityType extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected EntityType entityType;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected SearchQuery searchQuery;

  public SearchQueryEntityType(EntityType entityType, SearchQuery searchQuery) {
    super();
    this.entityType = entityType;
    this.searchQuery = searchQuery;
  }

  public SearchQueryEntityType() {
    super();
  }

  public EntityType getEntityType() {
    return entityType;
  }

  public void setEntityType(EntityType entityType) {
    this.entityType = entityType;
  }

  public SearchQuery getSearchQuery() {
    return searchQuery;
  }

  public void setSearchQuery(SearchQuery searchQuery) {
    this.searchQuery = searchQuery;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SearchQueryEntityType that = (SearchQueryEntityType) o;
    return Objects.equals(entityType, that.entityType)
        && Objects.equals(searchQuery, that.searchQuery);
  }

  @Override
  public int hashCode() {

    return Objects.hash(entityType, searchQuery);
  }
}
