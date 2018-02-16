package com.walterjwhite.index.modules;

import com.google.inject.AbstractModule;
import com.walterjwhite.google.guice.property.property.Property;
import com.walterjwhite.index.api.service.IndexMaintenanceService;
import com.walterjwhite.index.api.service.IndexSearchService;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.index.modules.elasticsearch.ElasticSearchHost;
import com.walterjwhite.index.modules.elasticsearch.ElasticSearchPort;
import com.walterjwhite.index.modules.elasticsearch.impl.ElasticSearchIndexMaintenanceService;
import com.walterjwhite.index.modules.elasticsearch.impl.ElasticSearchIndexSearchService;
import com.walterjwhite.index.modules.elasticsearch.impl.ElasticSearchIndexService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/** TODO: 1. support configuring the hosts and ports */
public class ElasticSearchModule extends AbstractModule {
  protected final TransportClient transportClient;

  public ElasticSearchModule(
      @Property(ElasticSearchHost.class) String elasticSearchHost,
      @Property(ElasticSearchPort.class) int elasticSearchPort)
      throws UnknownHostException {
    super();
    transportClient =
        new PreBuiltTransportClient(Settings.EMPTY)
            .addTransportAddress(
                new InetSocketTransportAddress(
                    InetAddress.getByName(elasticSearchHost), elasticSearchPort));
    //          .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"),
    // 9300));

    // on shutdown

    // transportClient.close();
  }

  @Override
  protected void configure() {
    bind(TransportClient.class).toInstance(transportClient);
    bind(IndexService.class).to(ElasticSearchIndexService.class);
    bind(IndexSearchService.class).to(ElasticSearchIndexSearchService.class);
    bind(IndexMaintenanceService.class).to(ElasticSearchIndexMaintenanceService.class);
  }
}
