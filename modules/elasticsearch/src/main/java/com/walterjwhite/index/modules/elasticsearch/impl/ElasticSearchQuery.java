package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.criteria.EntityTypeRepository;
import com.walterjwhite.datastore.criteria.Repository;
import com.walterjwhite.index.api.model.index.IndexRecord;
import com.walterjwhite.index.api.model.query.SearchQuery;
import com.walterjwhite.index.api.model.query.SearchQueryEntityType;
import com.walterjwhite.index.api.service.IndexNameService;
import com.walterjwhite.index.api.service.SearchQueryService;
import java.util.List;
import javax.inject.Provider;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;

public class ElasticSearchQuery implements SearchQueryService {
  protected final TransportClient transportClient;
  protected final SearchQuery searchQuery;
  protected final IndexNameService indexNameService;
  protected final Provider<EntityTypeRepository> entityTypeRepositoryProvider;
  protected final Provider<Repository> repositoryProvider;

  public ElasticSearchQuery(
      TransportClient transportClient,
      SearchQuery searchQuery,
      IndexNameService indexNameService,
      Provider<EntityTypeRepository> entityTypeRepositoryProvider,
      Provider<Repository> repositoryProvider) {
    super();
    this.transportClient = transportClient;
    this.searchQuery = searchQuery;
    this.indexNameService = indexNameService;
    this.entityTypeRepositoryProvider = entityTypeRepositoryProvider;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public long getTotal() {
    return searchQuery.getTotal();
  }

  @Override
  public List<IndexRecord> getMatchedRecords() {
    return searchQuery.getIndexRecords();
  }

  @Override
  public void execute() {
    final QueryBuilder queryBuilder =
        new ElasticSearchQueryBuilder().build(searchQuery.getPredicate());

    SearchRequestBuilder builder =
        transportClient
            .prepareSearch(searchQuery.getIndex().getName())
            .setTypes(getEntityTypes(searchQuery.getSearchQueryEntityTypes()))
            .setQuery(queryBuilder);

    if (searchQuery.getResultSize() > 0) builder.setSize(searchQuery.getResultSize());

    SearchResponse searchResponse =
        builder.setFrom(searchQuery.getResultOffset()).setExplain(true).get();

    searchQuery.setTotal(searchResponse.getHits().getTotalHits());
    searchQuery.setExecutionTime(searchResponse.getTookInMillis());

    SearchHit[] results = searchResponse.getHits().getHits();
    for (SearchHit hit : results) {
      searchQuery
          .getIndexRecords()
          .add(
              new IndexRecord(
                  searchQuery,
                  searchQuery.getIndex(),
                  entityTypeRepositoryProvider.get().findByName(hit.getType()),
                  hit.getId(),
                  hit.getScore()));
    }
  }

  final String[] getEntityTypes(final List<SearchQueryEntityType> entityTypes) {
    final String[] entityTypeNames = new String[entityTypes.size()];

    int i = 0;
    for (SearchQueryEntityType entityType : entityTypes)
      entityTypeNames[i++] = entityType.getEntityType().getName();

    return (entityTypeNames);
  }
}
