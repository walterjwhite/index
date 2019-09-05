package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.EntityReference;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public class IndexableRecord implements Serializable /*extends AbstractEntity*/ {
  protected Index index;
  protected EntityReference entityReference;

  // object to be serialized via JSON-serialization service
  protected transient Serializable object;
}
