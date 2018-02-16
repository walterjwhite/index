package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IndexEntityType extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Index index;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected EntityType entityType;

  public IndexEntityType(Index index, EntityType entityType) {
    super();
    this.index = index;
    this.entityType = entityType;
  }

  public IndexEntityType() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IndexEntityType that = (IndexEntityType) o;
    return Objects.equals(index, that.index) && Objects.equals(entityType, that.entityType);
  }

  @Override
  public int hashCode() {

    return Objects.hash(index, entityType);
  }

  @Override
  public String toString() {
    return "IndexEntityType{"
        + "index="
        + index.getName()
        + ", entityType="
        + entityType.getName()
        + '}';
  }
}
