package com.walterjwhite.index.api.model.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexRecord;
import com.walterjwhite.index.api.model.query.predicate.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@NoArgsConstructor
@Entity
public class SearchQuery extends AbstractEntity {
  @EqualsAndHashCode.Exclude
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  protected Index index;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "searchQuery", cascade = CascadeType.ALL)
  protected List<SearchQueryEntityType> searchQueryEntityTypes = new ArrayList<>();

  @EqualsAndHashCode.Exclude
  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false, updatable = false)
  protected Predicate predicate;

  @Column(nullable = false, updatable = false)
  protected LocalDateTime dateTime;

  @EqualsAndHashCode.Exclude
  @Column(updatable = false)
  protected int resultOffset = 0;

  @EqualsAndHashCode.Exclude
  @Column(updatable = false)
  protected int resultSize = -1;

  @EqualsAndHashCode.Exclude @Column protected long total;

  // TODO: add search results ...
  @EqualsAndHashCode.Exclude
  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable
  protected List<IndexRecord> indexRecords = new ArrayList<>();

  @EqualsAndHashCode.Exclude @Column protected long executionTime;

  public SearchQuery(Index index, EntityType entityType, Predicate predicate) {
    super();
    this.index = index;
    this.searchQueryEntityTypes.add(new SearchQueryEntityType(entityType, this));
    this.predicate = predicate;
    this.dateTime = LocalDateTime.now();
  }
}
