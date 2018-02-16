package com.walterjwhite.index.modules.elasticsearch.impl.callable;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.index.modules.elasticsearch.impl.ElasticSearchIndexService;
import com.walterjwhite.queue.api.job.AbstractCallableJob;

public abstract class AbstractElasticSearchCallable
    extends AbstractCallableJob<AbstractEntity, IndexActivity> {

  protected final ElasticSearchIndexService elasticSearchIndexService;

  // TODO: this is NOT set, where should we set it?
  protected IndexableRecord indexableRecord;

  //  protected Index index;
  //  protected EntityType entityType;
  //  protected String id;
  //  protected AbstractEntity entity;

  protected AbstractElasticSearchCallable(ElasticSearchIndexService elasticSearchIndexService) {
    super();
    this.elasticSearchIndexService = elasticSearchIndexService;
  }
}
