package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import java.io.ByteArrayOutputStream;
import javax.inject.Inject;
import javax.inject.Provider;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticSearchIndexUpdateService
    extends AbstractElasticSearchIndexService<UpdateResponse> {
  @Inject
  public ElasticSearchIndexUpdateService(
      JSONSerializationService serializationService,
      Provider<Repository> repositoryProvider,
      TransportClient transportClient) {
    super(serializationService, repositoryProvider, transportClient);
  }

  protected UpdateResponse doIndex(
      IndexableRecord indexableRecord, Index index, ByteArrayOutputStream byteArrayOutputStream) {
    return transportClient
        .prepareUpdate(
            index.getName(),
            indexableRecord.getEntityReference().getEntityType().getName(),
            Integer.toString(indexableRecord.getEntityReference().getEntityId()))
        .setDoc(byteArrayOutputStream.toByteArray(), XContentType.JSON)
        .get();
  }
}
