package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.model.query.SearchQuery;
import com.walterjwhite.index.api.service.IndexBridgeService;
import com.walterjwhite.index.api.service.IndexNameService;
import com.walterjwhite.index.api.service.SearchQueryService;
import com.walterjwhite.index.service.AbstractIndexSearchService;
import javax.inject.Inject;
import javax.inject.Provider;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticSearchIndexSearchService extends AbstractIndexSearchService {
  protected final RestHighLevelClient restHighLevelClient;
  protected final Provider<Repository> repositoryProvider;

  @Inject
  public ElasticSearchIndexSearchService(
      IndexNameService indexNameService,
      IndexBridgeService indexBridgeService,
      RestHighLevelClient restHighLevelClient,
      Provider<Repository> repositoryProvider) {
    super(indexBridgeService, indexNameService, repositoryProvider);
    this.restHighLevelClient = restHighLevelClient;
    this.repositoryProvider = repositoryProvider;
  }

  // TODO: 1. support multiple bridge services at a time
  @Override
  public SearchQueryService doSearch(SearchQuery searchQuery) throws Exception {
    return (new ElasticSearchQuery(
        restHighLevelClient, searchQuery, indexNameService, repositoryProvider));
  }
}
