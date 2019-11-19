package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.enumeration.IndexActionType;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.index.query.FindIndexByNameQuery;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.client.RestHighLevelClient;

@Getter
@RequiredArgsConstructor
public abstract class AbstractElasticSearchIndexService<ResponseType extends DocWriteResponse> {
  /** TODO: we MUST inject a JSON serialization service, we cannot use YAML here. */
  protected final JSONSerializationService serializationService;

  protected final Provider<Repository> repositoryProvider;
  protected final RestHighLevelClient restHighLevelClient;

  public IndexActivity index(IndexableRecord indexableRecord) throws Exception {
    Index index = indexableRecord.getIndex();

    if (index == null)
      index =
          repositoryProvider
              .get()
              .query(new FindIndexByNameQuery(FindIndexByNameQuery.DEFAULT_INDEX_NAME) /*,
                  PersistenceOption.Create*/);

    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    serializationService.serialize(indexableRecord.getObject(), baos);

    return prepareIndexActivity(indexableRecord, index, doIndex(indexableRecord, index, baos));
  }

  protected abstract ResponseType doIndex(
      IndexableRecord indexableRecord, Index index, ByteArrayOutputStream byteArrayOutputStream)
      throws IOException;

  protected IndexActivity prepareIndexActivity(
      IndexableRecord indexableRecord, Index index, ResponseType response) {
    return (new IndexActivity(
        index,
        indexableRecord.getEntityReference().getEntityType(),
        IndexActionType.Index,
        indexableRecord.getEntityReference().getEntityId(),
        response));
  }
}
