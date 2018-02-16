package com.walterjwhite.index.api.model.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexRecord;
import com.walterjwhite.index.api.model.query.predicate.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class SearchQuery extends AbstractEntity {
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  protected Index index;

  @OneToMany(mappedBy = "searchQuery", cascade = CascadeType.ALL)
  protected List<SearchQueryEntityType> searchQueryEntityTypes = new ArrayList<>();

  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false, updatable = false)
  protected Predicate predicate;

  @Column(nullable = false, updatable = false)
  protected LocalDateTime dateTime;

  @Column(updatable = false)
  protected int resultOffset = 0;

  @Column(updatable = false)
  protected int resultSize = -1;

  @Column protected long total;

  // TODO: add search results ...
  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable
  protected List<IndexRecord> indexRecords = new ArrayList<>();

  @Column protected long executionTime;

  public SearchQuery(Index index, EntityType entityType, Predicate predicate) {
    super();
    this.index = index;
    this.searchQueryEntityTypes.add(new SearchQueryEntityType(entityType, this));
    this.predicate = predicate;
    this.dateTime = LocalDateTime.now();
  }

  public SearchQuery() {
    super();
  }

  public Index getIndex() {
    return index;
  }

  public void setIndex(Index index) {
    this.index = index;
  }

  public List<SearchQueryEntityType> getSearchQueryEntityTypes() {
    return searchQueryEntityTypes;
  }

  public void setSearchQueryEntityTypes(List<SearchQueryEntityType> searchQueryEntityTypes) {
    this.searchQueryEntityTypes = searchQueryEntityTypes;
  }

  public Predicate getPredicate() {
    return predicate;
  }

  public void setPredicate(Predicate predicate) {
    this.predicate = predicate;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public int getResultOffset() {
    return resultOffset;
  }

  public void setResultOffset(int resultOffset) {
    this.resultOffset = resultOffset;
  }

  public int getResultSize() {
    return resultSize;
  }

  public void setResultSize(int resultSize) {
    this.resultSize = resultSize;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<IndexRecord> getIndexRecords() {
    return indexRecords;
  }

  public void setIndexRecords(List<IndexRecord> indexRecords) {
    this.indexRecords = indexRecords;
  }

  public long getExecutionTime() {
    return executionTime;
  }

  public void setExecutionTime(long executionTime) {
    this.executionTime = executionTime;
  }

  //  @Override
  //  public boolean equals(Object o) {
  //    if (this == o) return true;
  //    if (o == null || getClass() != o.getClass()) return false;
  //
  //    SearchQuery that = (SearchQuery) o;
  //
  //    if (resultOffset != that.resultOffset) return false;
  //    if (resultSize != that.resultSize) return false;
  //    if (index != null ? !index.equals(that.index) : that.index != null) return false;
  ////    if (searchQueryEntityTypes != null
  ////        ? !searchQueryEntityTypes.equals(that.searchQueryEntityTypes)
  ////        : that.searchQueryEntityTypes != null) return false;
  //    if(searchQueryEntityTypes != null){
  //
  //    }
  //
  //    return predicate != null ? predicate.equals(that.predicate) : that.predicate == null;
  //  }
  //
  //  @Override
  //  public int hashCode() {
  //    int result = index != null ? index.hashCode() : 0;
  ////    result = 31 * result + (searchQueryEntityTypes != null ? searchQueryEntityTypes.hashCode()
  // : 0);
  //    //result = 31 * result + (searchQueryEntityTypes != null ?
  // searchQueryEntityTypes.forEach(searchQueryEntityType ->
  // searchQueryEntityType.getEntityType().hashCode()): 0);
  //    if(searchQueryEntityTypes != null){
  //      for(SearchQueryEntityType searchQueryEntityType:searchQueryEntityTypes)
  //        result = 31 * result + searchQueryEntityType.getEntityType().hashCode();
  //    }
  //
  //    result = 31 * result + (predicate != null ? predicate.hashCode() : 0);
  //    result = 31 * result + resultOffset;
  //    result = 31 * result + resultSize;
  //    return result;
  //  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SearchQuery that = (SearchQuery) o;
    return Objects.equals(dateTime, that.dateTime);
  }

  @Override
  public int hashCode() {

    return Objects.hash(dateTime);
  }

  @Override
  public String toString() {
    return "SearchQuery{"
        + "index="
        + index
        + ", searchQueryEntityTypes="
        + searchQueryEntityTypes
        + ", predicate="
        + predicate
        + ", dateTime="
        + dateTime
        + ", from="
        + resultOffset
        + ", size="
        + resultSize
        + ", total="
        + total
        + ", indexRecords="
        + indexRecords
        + ", executionTime="
        + executionTime
        + '}';
  }
}
