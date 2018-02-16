package com.walterjwhite.index.api.service;

import java.io.Serializable;

public interface IndexBridgeService<
    RecordType extends Serializable,
    EntityType extends Serializable,
    EntityId extends Serializable> {
  RecordType get(final EntityType entityType, final EntityId id) throws Exception;
}
