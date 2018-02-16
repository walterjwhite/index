package com.walterjwhite.index.api.model.preference;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.persistence.Entity;

@Entity
public class SearchPreference extends AbstractEntity {
  //  protected User user;
  protected int resultsPerPage = 100;
}
