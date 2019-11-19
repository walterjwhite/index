package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Provider;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticSearchIndexUpdateService
    extends AbstractElasticSearchIndexService<UpdateResponse> {
  @Inject
  public ElasticSearchIndexUpdateService(
      JSONSerializationService serializationService,
      Provider<Repository> repositoryProvider,
      RestHighLevelClient restHighLevelClient) {
    super(serializationService, repositoryProvider, restHighLevelClient);
  }

  protected UpdateResponse doIndex(
      IndexableRecord indexableRecord, Index index, ByteArrayOutputStream byteArrayOutputStream)
      throws IOException {
    return restHighLevelClient.update(
        prepare(index, indexableRecord, byteArrayOutputStream), RequestOptions.DEFAULT);
  }

  protected UpdateRequest prepare(
      Index index, IndexableRecord indexableRecord, ByteArrayOutputStream byteArrayOutputStream) {
    return new UpdateRequest(
            index.getName(), Integer.toString(indexableRecord.getEntityReference().getEntityId()))
        .doc(byteArrayOutputStream.toByteArray(), XContentType.JSON);
  }
}
