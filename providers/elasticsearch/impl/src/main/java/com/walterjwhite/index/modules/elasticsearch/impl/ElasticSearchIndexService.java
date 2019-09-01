package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import javax.inject.Inject;
import javax.inject.Provider;
import org.elasticsearch.client.transport.TransportClient;

public class ElasticSearchIndexService implements IndexService {
  /** TODO: we MUST inject a JSON serialization service, we cannot use YAML here. */
  protected final JSONSerializationService serializationService;

  protected final Provider<Repository> repositoryProvider;
  protected final TransportClient transportClient;

  @Inject
  public ElasticSearchIndexService(
      TransportClient transportClient,
      JSONSerializationService serializationService,
      Provider<Repository> repositoryProvider) {
    super();
    this.transportClient = transportClient;
    this.serializationService = serializationService;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public IndexActivity index(IndexableRecord indexableRecord) throws Exception {
    return new ElasticSearchIndexIndexService(
            serializationService, repositoryProvider, transportClient)
        .index(indexableRecord);
  }

  @Override
  public IndexActivity update(IndexableRecord indexableRecord) throws Exception {
    return new ElasticSearchIndexUpdateService(
            serializationService, repositoryProvider, transportClient)
        .index(indexableRecord);
  }

  @Override
  public IndexActivity delete(IndexableRecord indexableRecord) throws Exception {
    return new ElasticSearchIndexDeleteService(
            serializationService, repositoryProvider, transportClient)
        .index(indexableRecord);
  }
}
