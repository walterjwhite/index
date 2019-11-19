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
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;

@Singleton
public class ElasticSearchIndexMaintenanceService extends AbstractIndexMaintenanceService {
  protected final RestHighLevelClient restHighLevelClient;

  @Inject
  public ElasticSearchIndexMaintenanceService(
      RestHighLevelClient restHighLevelClient,
      Provider<Repository> repositoryProvider,
      @Property(CreateIndexesOnStart.class) final boolean createIndexesOnStart,
      @Property(DeleteIndexesOnStop.class) final boolean deleteIndexesOnStop)
      throws IOException {
    super(repositoryProvider, createIndexesOnStart, deleteIndexesOnStop);
    this.restHighLevelClient = restHighLevelClient;
  }

  @Override
  protected void doCreate(Index index) throws IOException {
    restHighLevelClient
        .indices()
        .create(new CreateIndexRequest(index.getName()), RequestOptions.DEFAULT);
  }

  @Override
  protected void doDelete(Index index) throws IOException {
    restHighLevelClient
        .indices()
        .delete(new DeleteIndexRequest(index.getName()), RequestOptions.DEFAULT);
  }
}
