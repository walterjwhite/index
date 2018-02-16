package com.walterjwhite.index;

import com.google.inject.persist.Transactional;
import com.walterjwhite.datastore.criteria.Repository;
import com.walterjwhite.index.api.model.query.SearchQuery;
import com.walterjwhite.index.api.service.*;
import javax.inject.Provider;

public abstract class AbstractIndexSearchService implements IndexSearchService {
  protected final IndexBridgeService indexBridgeService;
  protected final IndexNameService indexNameService;
  protected final Provider<Repository> repositoryProvider;

  public AbstractIndexSearchService(
      IndexBridgeService indexBridgeService,
      IndexNameService indexNameService,
      Provider<Repository> repositoryProvider) {
    super();
    this.indexBridgeService = indexBridgeService;
    this.indexNameService = indexNameService;
    this.repositoryProvider = repositoryProvider;
  }

  @Transactional
  @Override
  public void search(SearchQuery searchQuery) throws Exception {
    // require user to specify the index
    //    searchQuery.setIndex(indexNameService.getIndex(searchQuery.getEntityType()));

    final SearchQueryService searchQueryService = doSearch(searchQuery);
    searchQueryService.execute();
    searchQuery.setTotal(searchQueryService.getTotal());

    // TODO: put this back
    //    for (final IndexRecord indexRecord : searchQueryService.getMatchedRecords()) {
    //      searchQuery
    //          .getIndexRecords()
    //          .add(
    //              indexBridgeService.get(
    //                  indexRecord.getEntityType(),
    // indexNameService.getEntityId(indexRecord.getId())));
    //    }

    // update the search query with the response
    repositoryProvider.get().merge(searchQuery);
  }

  protected abstract SearchQueryService doSearch(SearchQuery searchQuery) throws Exception;
}
