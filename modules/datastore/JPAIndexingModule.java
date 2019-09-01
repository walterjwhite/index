package com.walterjwhite.index.modules.jpa;

import com.google.inject.AbstractModule;
import com.walterjwhite.index.api.service.IndexBridgeService;
import com.walterjwhite.index.api.service.IndexNameService;

public class JPAIndexingModule extends AbstractModule {
  @Override
  protected void configure() {
    //        bind(IndexingRecordReader.class);
    bind(IndexBridgeService.class).to(JPAIndexBridgeService.class);
    bind(IndexNameService.class).to(JPAIndexNameService.class);

    bind(JPAIndexEntityRepository.class);
  }
}
