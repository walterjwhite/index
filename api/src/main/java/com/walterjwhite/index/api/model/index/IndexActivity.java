package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.enumeration.IndexActionType;
import javax.persistence.*;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IndexActivity extends AbstractEntity {
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  protected Index index;

  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  protected EntityType entityType;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false)
  protected IndexActionType indexActionType;

  @Column(nullable = false, updatable = false)
  protected Integer indexId;

  @EqualsAndHashCode.Exclude
  @Lob
  @Column(nullable = false, updatable = false)
  protected Object indexData;
}
