package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Provider;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticSearchIndexIndexService
    extends AbstractElasticSearchIndexService<IndexResponse> {
  @Inject
  public ElasticSearchIndexIndexService(
      JSONSerializationService serializationService,
      Provider<Repository> repositoryProvider,
      RestHighLevelClient restHighLevelClient) {
    super(serializationService, repositoryProvider, restHighLevelClient);
  }

  protected IndexResponse doIndex(
      IndexableRecord indexableRecord, Index index, ByteArrayOutputStream byteArrayOutputStream)
      throws IOException {
    return restHighLevelClient.index(
        prepare(index, indexableRecord, byteArrayOutputStream), RequestOptions.DEFAULT);
  }

  protected IndexRequest prepare(
      Index index, IndexableRecord indexableRecord, ByteArrayOutputStream byteArrayOutputStream) {
    return new IndexRequest(index.getName())
        .id(Integer.toString(indexableRecord.getEntityReference().getEntityId()))
        .source(byteArrayOutputStream.toByteArray(), XContentType.JSON);
  }
}
