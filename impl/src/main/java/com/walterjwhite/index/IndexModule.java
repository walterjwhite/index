package com.walterjwhite.index;

import com.google.inject.AbstractModule;
import com.walterjwhite.encryption.impl.EncryptionModule;
import com.walterjwhite.index.api.service.IndexRepository;

public class IndexModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(IndexRepository.class);

    install(new EncryptionModule());
  }
}
