package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.api.enumeration.IndexActionType;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.index.api.service.IndexRepository;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import javax.inject.Inject;
import javax.inject.Provider;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticSearchIndexService implements IndexService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchIndexService.class);

  /** TODO: we MUST inject a JSON serialization service, we cannot use YAML here. */
  protected final JSONSerializationService serializationService;

  protected final Provider<IndexRepository> indexRepositoryProvider;
  protected final TransportClient transportClient;

  @Inject
  public ElasticSearchIndexService(
      TransportClient transportClient,
      JSONSerializationService serializationService,
      Provider<IndexRepository> indexRepositoryProvider) {
    super();
    this.transportClient = transportClient;
    this.serializationService = serializationService;
    this.indexRepositoryProvider = indexRepositoryProvider;
  }

  @Override
  public IndexActivity index(IndexableRecord indexableRecord) throws Exception {
    Index index = indexableRecord.getIndex();

    if (index == null) index = indexRepositoryProvider.get().getDefault();

    final byte[] serializedData = serializationService.serialize(indexableRecord.getObject());

    final IndexResponse indexResponse =
        transportClient
            .prepareIndex(
                index.getName(), indexableRecord.getEntityType().getName(), indexableRecord.getId())
            .setSource(serializedData, XContentType.JSON)
            .get();
    return (new IndexActivity(
        index,
        indexableRecord.getEntityType(),
        IndexActionType.Index,
        indexableRecord.getId(),
        indexResponse));
  }

  @Override
  public IndexActivity update(IndexableRecord indexableRecord) throws Exception {
    Index index = indexableRecord.getIndex();

    if (index == null) index = indexRepositoryProvider.get().getDefault();

    final byte[] serializedData = serializationService.serialize(indexableRecord.getObject());

    final UpdateResponse updateResponse =
        transportClient
            .prepareUpdate(
                index.getName(), indexableRecord.getEntityType().getName(), indexableRecord.getId())
            .setDoc(serializedData, XContentType.JSON)
            .get();
    return (new IndexActivity(
        index,
        indexableRecord.getEntityType(),
        IndexActionType.Index,
        indexableRecord.getId(),
        updateResponse));
  }

  @Override
  public IndexActivity delete(IndexableRecord indexableRecord) throws Exception {
    Index index = indexableRecord.getIndex();

    if (index == null) index = indexRepositoryProvider.get().getDefault();

    final DeleteResponse deleteResponse =
        transportClient
            .prepareDelete(
                index.getName(), indexableRecord.getEntityType().getName(), indexableRecord.getId())
            .get();
    return (new IndexActivity(
        index,
        indexableRecord.getEntityType(),
        IndexActionType.Index,
        indexableRecord.getId(),
        deleteResponse));
  }
}
