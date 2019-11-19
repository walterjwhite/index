package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.modules.elasticsearch.property.ElasticSearchNodes;
import com.walterjwhite.interruptable.Interruptable;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;
import javax.inject.Provider;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

// TODO: does it matter if we have multiple clients to the same endpoint, I originally set this up
// as a singleton, does it need to be?
// @Singleton
public class RestHighLevelClientClientProvider
    implements Provider<RestHighLevelClient>, Interruptable {
  protected final RestHighLevelClient restHighLevelClient;

  @Inject
  public RestHighLevelClientClientProvider(
      @Property(ElasticSearchNodes.class) String elasticSearchNodes) {
    super();
    restHighLevelClient =
        new RestHighLevelClient(RestClient.builder(getHttpHosts(getNodes(elasticSearchNodes))));
  }

  private static String[] getNodes(final String nodeString) {
    return nodeString.split(",");
  }

  private static HttpHost[] getHttpHosts(final String[] nodes) {
    final HttpHost[] httpHosts = new HttpHost[nodes.length];
    for (int i = 0; i < nodes.length; i++) {
      httpHosts[i] = HttpHost.create(nodes[i]);
    }

    return httpHosts;
  }

  @Override
  public RestHighLevelClient get() {
    return restHighLevelClient;
  }

  @Override
  public void interrupt() {
    if (restHighLevelClient != null) {
      try {
        restHighLevelClient.close();
      } catch (Exception e) {
      }
    }
  }
}
