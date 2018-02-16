package com.walterjwhite.index.modules.elasticsearch;

import com.walterjwhite.google.guice.property.property.DefaultValue;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

public interface ElasticSearchPort extends GuiceProperty {
  @DefaultValue int Default = 9300;
}
