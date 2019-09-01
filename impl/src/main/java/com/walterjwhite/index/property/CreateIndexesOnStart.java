package com.walterjwhite.index.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface CreateIndexesOnStart extends ConfigurableProperty {
  @DefaultValue boolean Default = true;
}
