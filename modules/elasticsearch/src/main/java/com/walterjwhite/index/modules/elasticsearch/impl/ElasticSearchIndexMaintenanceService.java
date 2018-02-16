package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.service.IndexMaintenanceService;
import com.walterjwhite.index.api.service.IndexRepository;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.elasticsearch.client.transport.TransportClient;

@Singleton
public class ElasticSearchIndexMaintenanceService implements IndexMaintenanceService {
  protected final TransportClient transportClient;
  protected final Provider<IndexRepository> indexRepositoryProvider;

  @Inject
  public ElasticSearchIndexMaintenanceService(
      TransportClient transportClient, Provider<IndexRepository> indexRepositoryProvider)
      throws IOException {
    this.transportClient = transportClient;
    this.indexRepositoryProvider = indexRepositoryProvider;
  }

  @Override
  public void create(Index index) {
    transportClient.admin().indices().prepareCreate(index.getName()).get();
  }

  @Override
  public void create() throws IOException {
    create(indexRepositoryProvider.get().getDefault());
  }

  @Override
  public void delete(Index index) {
    transportClient.admin().indices().prepareDelete(index.getName()).get();
    // TODO: set a field/column to indicate the index is deleted
  }

  @Override
  public void delete() throws IOException {
    delete(indexRepositoryProvider.get().getDefault());
  }
}
