package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.enumeration.IndexActionType;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class IndexActivity extends AbstractEntity {

  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  protected Index index;

  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  protected EntityType entityType;

  @Column(nullable = false, updatable = false)
  protected IndexActionType indexActionType;

  @Column(nullable = false, updatable = false)
  protected String indexId;

  @Lob
  @Column(nullable = false, updatable = false)
  protected Object indexData;

  public IndexActivity(
      Index index,
      EntityType entityType,
      IndexActionType indexActionType,
      String indexId,
      Object indexData) {
    super();
    this.index = index;
    this.entityType = entityType;
    this.indexActionType = indexActionType;
    this.indexData = indexData;
  }

  public IndexActivity() {
    super();
  }

  public Index getIndex() {
    return index;
  }

  public void setIndex(Index index) {
    this.index = index;
  }

  public EntityType getEntityType() {
    return entityType;
  }

  public void setEntityType(EntityType entityType) {
    this.entityType = entityType;
  }

  public IndexActionType getIndexActionType() {
    return indexActionType;
  }

  public void setIndexActionType(IndexActionType indexActionType) {
    this.indexActionType = indexActionType;
  }

  public String getIndexId() {
    return indexId;
  }

  public void setIndexId(String indexId) {
    this.indexId = indexId;
  }

  public Object getIndexData() {
    return indexData;
  }

  public void setIndexData(Object indexData) {
    this.indexData = indexData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IndexActivity that = (IndexActivity) o;
    return Objects.equals(index, that.index)
        && Objects.equals(entityType, that.entityType)
        && indexActionType == that.indexActionType
        && Objects.equals(indexId, that.indexId);
  }

  @Override
  public int hashCode() {

    return Objects.hash(index, entityType, indexActionType, indexId);
  }
}
