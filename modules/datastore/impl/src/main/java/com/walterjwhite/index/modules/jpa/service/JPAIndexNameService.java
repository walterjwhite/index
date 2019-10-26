package com.walterjwhite.index.modules.jpa.service;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.index.api.service.IndexNameService;

/** @deprecated This class should live in the index project as a JPA module */
public class JPAIndexNameService
    implements IndexNameService<Class<? extends AbstractEntity>, String> {

  @Override
  public String getEntityTypeString(Class<? extends AbstractEntity> entityClass) {
    return entityClass.getName();
  }

  @Override
  public Class<? extends AbstractEntity> getEntityType(String entityTypeString) {
    try {
      return (Class<? extends AbstractEntity>) Class.forName(entityTypeString);
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("Unable to map entity class", e);
    }
  }

  @Override
  public String getEntityId(String indexId) {
    return indexId;
  }

  @Override
  public String getIndexId(String entityId) {
    return entityId;
  }
}
