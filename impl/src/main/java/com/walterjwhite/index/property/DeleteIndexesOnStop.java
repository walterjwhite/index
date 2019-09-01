package com.walterjwhite.index.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface DeleteIndexesOnStop extends ConfigurableProperty {
  @DefaultValue boolean Default = true;
}
