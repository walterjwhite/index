package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Provider;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticSearchIndexDeleteService
    extends AbstractElasticSearchIndexService<DeleteResponse> {
  @Inject
  public ElasticSearchIndexDeleteService(
      JSONSerializationService serializationService,
      Provider<Repository> repositoryProvider,
      RestHighLevelClient restHighLevelClient) {
    super(serializationService, repositoryProvider, restHighLevelClient);
  }

  protected DeleteResponse doIndex(
      IndexableRecord indexableRecord, Index index, ByteArrayOutputStream byteArrayOutputStream)
      throws IOException {
    return restHighLevelClient.delete(prepare(index, indexableRecord), RequestOptions.DEFAULT);
  }

  protected DeleteRequest prepare(Index index, IndexableRecord indexableRecord) {
    return new DeleteRequest(
        index.getName(), Integer.toString(indexableRecord.getEntityReference().getEntityId()));
  }
}
