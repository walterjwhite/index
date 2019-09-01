// package com.walterjwhite.index;
//
// import com.walterjwhite.index.api.model.index.Index;
// import com.walterjwhite.index.api.model.index.IndexActivity;
// import com.walterjwhite.index.api.service.*;
// import java.io.Serializable;
//
// public abstract class AbstractIndexService implements IndexService {
//  protected final IndexBridgeService indexBridgeService;
//  protected final IndexNameService indexNameService;
//
//  public AbstractIndexService(
//      IndexBridgeService indexBridgeService, IndexNameService indexNameService) {
//    super();
//    this.indexBridgeService = indexBridgeService;
//    this.indexNameService = indexNameService;
//  }
//
//  @Override
//  public IndexActivity index(Index index, Serializable id, Serializable object)
//      throws Exception {
//    return doIndex(index, indexNameService.getIndexId(id), object);
//  }
//
//  protected abstract IndexActivity doIndex(Index index, String indexId, Serializable data)
//      throws Exception;
//
//  @Override
//  public IndexActivity update(Index index, Serializable id, Serializable object)
//      throws Exception {
//    return doUpdate(
//        index, indexNameService.getIndexId(id), object);
//  }
//
//  protected abstract IndexActivity doUpdate(Index index, String indexId, Serializable data)
//      throws Exception;
//
//  @Override
//  public IndexActivity delete(Index index, Serializable id) throws Exception {
//    return doDelete(index, indexNameService.getIndexId(id));
//  }
//
//  protected abstract IndexActivity doDelete(Index index, String indexId) throws Exception;
// }
