package com.walterjwhite.index.api.service;

import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.api.model.index.IndexableRecord;

public interface IndexService {

  IndexActivity index(IndexableRecord indexableRecord) throws Exception;

  IndexActivity update(IndexableRecord indexableRecord) throws Exception;

  IndexActivity delete(IndexableRecord indexableRecord) throws Exception;

  /** returns the Entity ID of the object we put in (JPA=>String, CSV=>Long)* */
  //  Serializable get(Serializable entityType, String indexId) throws Exception;
}
