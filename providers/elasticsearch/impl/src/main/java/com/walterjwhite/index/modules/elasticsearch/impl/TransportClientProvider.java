package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.modules.elasticsearch.ElasticSearchHost;
import com.walterjwhite.index.modules.elasticsearch.ElasticSearchPort;
import com.walterjwhite.interruptable.Interruptable;
import com.walterjwhite.property.impl.annotation.Property;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.inject.Inject;
import javax.inject.Provider;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

// TODO: does it matter if we have multiple clients to the same endpoint, I originally set this up
// as a singleton, does it need to be?
// @Singleton
public class TransportClientProvider implements Provider<TransportClient>, Interruptable {
  protected final TransportClient transportClient;

  @Inject
  public TransportClientProvider(
      @Property(ElasticSearchHost.class) String elasticSearchHost,
      @Property(ElasticSearchPort.class) int elasticSearchPort)
      throws UnknownHostException {
    super();
    transportClient =
        new PreBuiltTransportClient(Settings.EMPTY)
            .addTransportAddress(
                new TransportAddress(InetAddress.getByName(elasticSearchHost), elasticSearchPort));
  }

  @Override
  public TransportClient get() {
    return transportClient;
  }

  @Override
  public void interrupt() {
    if (transportClient != null) {
      try {
        transportClient.close();
      } catch (Exception e) {
      }
    }
  }
}
