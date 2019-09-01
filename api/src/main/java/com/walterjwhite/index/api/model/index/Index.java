package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.*;

/** 2 types: 1. Entity 2. CSV file */
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
// @PersistenceCapable
@NoArgsConstructor
@Entity
public class Index extends AbstractNamedEntity {
  @EqualsAndHashCode.Exclude
  //  protected final String entityType;
  @OneToMany(mappedBy = "index", cascade = CascadeType.ALL)
  protected List<IndexEntityType> indexEntityTypes = new ArrayList<>();

  public Index(String name) {
    super(name);
  }
}
