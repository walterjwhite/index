package com.walterjwhite.index.modules.elasticsearch;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface ElasticSearchHost extends ConfigurableProperty {
  @DefaultValue String Default = "localhost";
}
