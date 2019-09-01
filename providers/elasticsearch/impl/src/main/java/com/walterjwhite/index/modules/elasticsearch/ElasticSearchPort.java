package com.walterjwhite.index.modules.elasticsearch;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface ElasticSearchPort extends ConfigurableProperty {
  @DefaultValue int Default = 9300;
}
