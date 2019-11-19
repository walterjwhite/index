package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.datastore.query.entityType.FindEntityTypeByNameQuery;
import com.walterjwhite.index.api.model.index.IndexRecord;
import com.walterjwhite.index.api.model.query.SearchQuery;
import com.walterjwhite.index.api.model.query.SearchQueryEntityType;
import com.walterjwhite.index.api.service.IndexNameService;
import com.walterjwhite.index.api.service.SearchQueryService;
import java.io.IOException;
import java.util.List;
import javax.inject.Provider;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;

// TODO: convert this into a builder of type SearchResponse
@Deprecated
public class ElasticSearchQuery implements SearchQueryService {
  protected final RestHighLevelClient restHighLevelClient;
  protected final SearchQuery searchQuery;
  protected final IndexNameService indexNameService;
  protected final Provider<Repository> repositoryProvider;

  public ElasticSearchQuery(
      RestHighLevelClient restHighLevelClient,
      SearchQuery searchQuery,
      IndexNameService indexNameService,
      Provider<Repository> repositoryProvider) {
    super();
    this.restHighLevelClient = restHighLevelClient;
    this.searchQuery = searchQuery;
    this.indexNameService = indexNameService;
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
  public void execute() throws IOException {
    final SearchRequest searchRequest = prepareSearch();
    final SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
  }

  protected SearchRequest prepareSearch() {
    final QueryBuilder queryBuilder =
        new ElasticSearchQueryBuilder().build(searchQuery.getPredicate());

    // TODO: fix
    //    if (searchQuery.getResultSize() > 0) builder.setSize(searchQuery.getResultSize());
    //
    //    builder.setFrom(searchQuery.getResultOffset()).setExplain(true).get();
    //    searchQuery.setTotal(searchResponse.getHits().getTotalHits());
    // @TODO: store this as a duration for greater granularity
    // TODO: fix
    //    searchQuery.setExecutionTime(searchResponse.getTook().millis());

    // search option
    // sort option
    // SearchAction searchAction = SearchAction.INSTANCE;
    //    SearchRequestBuilder builder = new SearchRequestBuilder(
    //        restHighLevelClient., searchAction);
    //
    //            .search(new SearchRequest(), RequestOptions.DEFAULT);

    final SearchRequest searchRequest = new SearchRequest(searchQuery.getIndex().getName());
    // TODO: fix
    // searchRequest.source(queryBuilder);

    return searchRequest;
  }

  protected final String[] getEntityTypes(final List<SearchQueryEntityType> entityTypes) {
    final String[] entityTypeNames = new String[entityTypes.size()];

    int i = 0;
    for (SearchQueryEntityType entityType : entityTypes)
      entityTypeNames[i++] = entityType.getEntityType().getName();

    return (entityTypeNames);
  }

  protected void prepareResults(final SearchResponse searchResponse) {
    SearchHit[] results = searchResponse.getHits().getHits();
    for (SearchHit hit : results) {
      searchQuery
          .getIndexRecords()
          .add(
              new IndexRecord(
                  searchQuery,
                  searchQuery.getIndex(),
                  repositoryProvider.get().query(new FindEntityTypeByNameQuery(hit.getType()) /*,
                          PersistenceOption.Create*/),
                  hit.getId(),
                  hit.getScore()));
    }
  }
}
