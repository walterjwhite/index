package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.property.CreateIndexesOnStart;
import com.walterjwhite.index.property.DeleteIndexesOnStop;
import com.walterjwhite.index.service.AbstractIndexMaintenanceService;
import com.walterjwhite.property.impl.annotation.Property;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.elasticsearch.client.transport.TransportClient;

@Singleton
public class ElasticSearchIndexMaintenanceService extends AbstractIndexMaintenanceService {
  protected final TransportClient transportClient;

  @Inject
  public ElasticSearchIndexMaintenanceService(
      TransportClient transportClient,
      Provider<Repository> repositoryProvider,
      @Property(CreateIndexesOnStart.class) final boolean createIndexesOnStart,
      @Property(DeleteIndexesOnStop.class) final boolean deleteIndexesOnStop)
      throws IOException {
    super(repositoryProvider, createIndexesOnStart, deleteIndexesOnStop);
    this.transportClient = transportClient;
  }

  @Override
  protected void doCreate(Index index) {
    transportClient.admin().indices().prepareCreate(index.getName()).get();
  }

  @Override
  protected void doDelete(Index index) {
    transportClient.admin().indices().prepareDelete(index.getName()).get();
  }
}
