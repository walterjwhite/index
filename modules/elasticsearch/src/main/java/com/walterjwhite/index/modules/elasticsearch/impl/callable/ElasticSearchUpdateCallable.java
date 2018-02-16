package com.walterjwhite.index.modules.elasticsearch.impl.callable;

import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.modules.elasticsearch.impl.ElasticSearchIndexService;
import javax.inject.Inject;

public class ElasticSearchUpdateCallable extends AbstractElasticSearchCallable {
  @Inject
  public ElasticSearchUpdateCallable(ElasticSearchIndexService elasticSearchIndexService) {
    super(elasticSearchIndexService);
  }

  public IndexActivity call() throws Exception {
    return (elasticSearchIndexService.update(indexableRecord));
  }

  @Override
  protected boolean isRetryable(Throwable thrown) {
    return false;
  }
}
