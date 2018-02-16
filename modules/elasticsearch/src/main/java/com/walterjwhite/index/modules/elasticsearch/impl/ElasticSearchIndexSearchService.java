package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.criteria.EntityTypeRepository;
import com.walterjwhite.datastore.criteria.Repository;
import com.walterjwhite.index.AbstractIndexSearchService;
import com.walterjwhite.index.api.model.query.SearchQuery;
import com.walterjwhite.index.api.service.IndexBridgeService;
import com.walterjwhite.index.api.service.IndexNameService;
import com.walterjwhite.index.api.service.SearchQueryService;
import javax.inject.Inject;
import javax.inject.Provider;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticSearchIndexSearchService extends AbstractIndexSearchService {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(ElasticSearchIndexSearchService.class);
  protected final TransportClient transportClient;
  protected final Provider<EntityTypeRepository> entityTypeRepositoryProvider;
  protected final Provider<Repository> repositoryProvider;

  @Inject
  public ElasticSearchIndexSearchService(
      IndexNameService indexNameService,
      IndexBridgeService indexBridgeService,
      TransportClient transportClient,
      Provider<EntityTypeRepository> entityTypeRepositoryProvider,
      Provider<Repository> repositoryProvider) {
    super(indexBridgeService, indexNameService, repositoryProvider);
    this.transportClient = transportClient;
    this.entityTypeRepositoryProvider = entityTypeRepositoryProvider;
    this.repositoryProvider = repositoryProvider;
  }

  /**
   * 1. this can ONLY support 1 bridge service at a time 2. TODO: support multiple bridge services
   * at a time based on the data type
   *
   * @throws Exception
   */
  @Override
  public SearchQueryService doSearch(SearchQuery searchQuery) throws Exception {
    return (new ElasticSearchQuery(
        transportClient,
        searchQuery,
        indexNameService,
        entityTypeRepositoryProvider,
        repositoryProvider));
  }
}
