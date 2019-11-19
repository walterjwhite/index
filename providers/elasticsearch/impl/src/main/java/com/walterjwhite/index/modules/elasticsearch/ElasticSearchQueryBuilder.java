package com.walterjwhite.index.modules.elasticsearch;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.QueryBuilder;
import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import javax.persistence.EntityManager;

public class ElasticSearchQueryBuilder<
        EntityType extends AbstractEntity,
        // the intent here was to store references on references, but is that worth it?
        ResultType extends /*Serializable*/ Object,
        QueryConfigurationType extends QueryConfiguration<EntityType, ResultType>>
    implements QueryBuilder<EntityType, ResultType, QueryConfigurationType, EntityManager> {

  @Override
  public ResultType get(EntityManager entityManager, QueryConfigurationType query) {
    return null;
  }
}
