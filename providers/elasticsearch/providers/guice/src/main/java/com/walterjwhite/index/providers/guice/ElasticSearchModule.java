package com.walterjwhite.index.providers.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.index.api.service.IndexMaintenanceService;
import com.walterjwhite.index.api.service.IndexSearchService;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.index.modules.elasticsearch.impl.ElasticSearchIndexMaintenanceService;
import com.walterjwhite.index.modules.elasticsearch.impl.ElasticSearchIndexSearchService;
import com.walterjwhite.index.modules.elasticsearch.impl.ElasticSearchIndexService;
import com.walterjwhite.index.modules.elasticsearch.impl.TransportClientProvider;
import com.walterjwhite.interruptable.annotation.InterruptableService;
import org.elasticsearch.client.transport.TransportClient;

/** TODO: 1. support many hosts and ports */
@InterruptableService
public class ElasticSearchModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(TransportClient.class).toProvider(TransportClientProvider.class);
    bind(IndexService.class).to(ElasticSearchIndexService.class);
    bind(IndexSearchService.class).to(ElasticSearchIndexSearchService.class);
    bind(IndexMaintenanceService.class).to(ElasticSearchIndexMaintenanceService.class);
  }
}
