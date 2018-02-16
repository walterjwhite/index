package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/** 2 types: 1. Entity 2. CSV file */
@Entity
public class Index extends AbstractNamedEntity {
  //  protected final String entityType;
  @OneToMany(mappedBy = "index", cascade = CascadeType.ALL)
  protected List<IndexEntityType> indexEntityTypes = new ArrayList<>();

  public Index(String name) {
    super(name);
  }

  public Index() {
    super();
  }

  public List<IndexEntityType> getIndexEntityTypes() {
    return indexEntityTypes;
  }

  public void setIndexEntityTypes(List<IndexEntityType> indexEntityTypes) {
    this.indexEntityTypes = indexEntityTypes;
  }
}
