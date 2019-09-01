package com.walterjwhite.index.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.index.api.service.IndexBridgeService;
import com.walterjwhite.index.api.service.IndexNameService;
import com.walterjwhite.index.modules.jpa.callable.IndexEventProcessorDeleteRunnable;
import com.walterjwhite.index.modules.jpa.callable.IndexEventProcessorIndexRunnable;
import com.walterjwhite.index.modules.jpa.callable.IndexEventProcessorUpdateRunnable;
import com.walterjwhite.index.modules.jpa.service.AbstractJPAIndexBridgeService;
import com.walterjwhite.index.modules.jpa.service.JPAIndexNameService;

public class JPAIndexModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(IndexEventProcessorDeleteRunnable.class);
    bind(IndexEventProcessorIndexRunnable.class);
    bind(IndexEventProcessorUpdateRunnable.class);

    bind(IndexBridgeService.class).to(AbstractJPAIndexBridgeService.class);
    bind(IndexNameService.class).to(JPAIndexNameService.class);
  }
}
