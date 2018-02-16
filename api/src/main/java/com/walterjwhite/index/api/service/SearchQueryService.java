package com.walterjwhite.index.api.service;

import com.walterjwhite.index.api.model.index.IndexRecord;
import java.util.List;

public interface SearchQueryService {
  long getTotal();

  List<IndexRecord> getMatchedRecords();

  void execute();
}
