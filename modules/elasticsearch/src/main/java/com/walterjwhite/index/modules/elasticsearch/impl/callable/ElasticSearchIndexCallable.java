package com.walterjwhite.index.modules.elasticsearch.impl.callable;

import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.modules.elasticsearch.impl.ElasticSearchIndexService;
import javax.inject.Inject;

public class ElasticSearchIndexCallable extends AbstractElasticSearchCallable {
  @Inject
  public ElasticSearchIndexCallable(ElasticSearchIndexService elasticSearchIndexService) {
    super(elasticSearchIndexService);
  }

  public IndexActivity call() throws Exception {
    return (elasticSearchIndexService.index(indexableRecord));
  }

  @Override
  protected boolean isRetryable(Throwable thrown) {
    return false;
  }
}
