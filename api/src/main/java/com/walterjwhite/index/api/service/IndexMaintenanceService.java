package com.walterjwhite.index.api.service;

import com.walterjwhite.index.api.model.index.Index;
import java.io.IOException;

public interface IndexMaintenanceService {
  void create(Index index) throws IOException;

  void create() throws IOException;

  void delete(Index index) throws IOException;

  void delete() throws IOException;
}
