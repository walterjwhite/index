package com.walterjwhite.index.modules.jpa.service;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.infrastructure.inject.core.annotation.Secondary;
import javax.inject.Inject;

/** @deprecated This class should live in the index project as a JPA module */
public class IntegratedJPAIndexBridgeService extends AbstractJPAIndexBridgeService {
  @Inject
  public IntegratedJPAIndexBridgeService(@Secondary Repository repository) {
    super(repository);
  }
}
