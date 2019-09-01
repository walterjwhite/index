package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import java.io.ByteArrayOutputStream;
import javax.inject.Inject;
import javax.inject.Provider;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;

public class ElasticSearchIndexDeleteService
    extends AbstractElasticSearchIndexService<DeleteResponse> {
  @Inject
  public ElasticSearchIndexDeleteService(
      JSONSerializationService serializationService,
      Provider<Repository> repositoryProvider,
      TransportClient transportClient) {
    super(serializationService, repositoryProvider, transportClient);
  }

  protected DeleteResponse doIndex(
      IndexableRecord indexableRecord, Index index, ByteArrayOutputStream byteArrayOutputStream) {
    return transportClient
        .prepareDelete(
            index.getName(),
            indexableRecord.getEntityReference().getEntityType().getName(),
            Integer.toString(indexableRecord.getEntityReference().getEntityId()))
        .get();
  }
}
