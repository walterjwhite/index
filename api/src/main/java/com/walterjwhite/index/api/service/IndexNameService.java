package com.walterjwhite.index.api.service;

import java.io.Serializable;

public interface IndexNameService<
    /*TargetType extends Serializable,*/ EntityType extends Serializable,
    EntityId extends Serializable> {

  //  Index getIndex(final EntityType entityType);
  //
  String getEntityTypeString(EntityType entityClass);

  EntityType getEntityType(final String entityType);

  EntityId getEntityId(final String indexId);

  String getIndexId(final EntityId entityId);
}
