package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.model.query.SearchQuery;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

  @Column(nullable = false, updatable = false)
  protected double score;

  public IndexRecord(
      SearchQuery searchQuery, Index index, EntityType entityType, String entityId, double score) {
    super();
    this.searchQuery = searchQuery;
    this.index = index;
    this.entityType = entityType;
    this.entityId = entityId;
    this.score = score;
  }

  public IndexRecord() {
    super();
  }

  public SearchQuery getSearchQuery() {
    return searchQuery;
  }

  public void setSearchQuery(SearchQuery searchQuery) {
    this.searchQuery = searchQuery;
  }

  public Index getIndex() {
    return index;
  }

  public void setIndex(Index index) {
    this.index = index;
  }

  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public EntityType getEntityType() {
    return entityType;
  }

  public void setEntityType(EntityType entityType) {
    this.entityType = entityType;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IndexRecord that = (IndexRecord) o;
    return Objects.equals(searchQuery, that.searchQuery)
        && Objects.equals(index, that.index)
        && Objects.equals(entityType, that.entityType)
        && Objects.equals(entityId, that.entityId);
  }

  @Override
  public int hashCode() {

    return Objects.hash(searchQuery, index, entityType, entityId);
  }

  @Override
  public String toString() {
    return "IndexRecord{"
        + "searchQuery="
        + searchQuery.getId()
        + ", index="
        + index
        + ", entityType="
        + entityType
        + ", entityId='"
        + entityId
        + '\''
        + ", score="
        + score
        + '}';
  }
}
