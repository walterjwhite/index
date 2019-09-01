package com.walterjwhite.index.modules.jpa.service;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.infrastructure.inject.core.annotation.Primary;
import javax.inject.Inject;

/** @deprecated This class should live in the index project as a JPA module */
public class StandAloneJPAIndexBridgeService extends AbstractJPAIndexBridgeService {
  @Inject
  public StandAloneJPAIndexBridgeService(@Primary Repository repository) {
    super(repository);
  }
}
