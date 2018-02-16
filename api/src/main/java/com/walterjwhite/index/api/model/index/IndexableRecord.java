package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.EntityType;
import java.io.Serializable;
import java.util.Objects;

public class IndexableRecord implements Serializable {
  protected final Index index;
  protected final EntityType entityType;
  protected final String id;
  // object to be serialized via JSON-serialization service
  protected final Serializable object;

  public IndexableRecord(Index index, EntityType entityType, String id, Serializable object) {
    this.index = index;
    this.entityType = entityType;
    this.id = id;
    this.object = object;
  }

  public Index getIndex() {
    return index;
  }

  public EntityType getEntityType() {
    return entityType;
  }

  public String getId() {
    return id;
  }

  public Serializable getObject() {
    return object;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IndexableRecord that = (IndexableRecord) o;
    return Objects.equals(index, that.index)
        && Objects.equals(entityType, that.entityType)
        && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(index, entityType, id);
  }
}
