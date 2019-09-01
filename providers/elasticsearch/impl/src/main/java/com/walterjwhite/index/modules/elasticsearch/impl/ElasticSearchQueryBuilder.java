package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.api.model.query.Conjunction;
import com.walterjwhite.index.api.model.query.predicate.AttributePredicate;
import com.walterjwhite.index.api.model.query.predicate.GroupPredicate;
import com.walterjwhite.index.api.model.query.predicate.Predicate;
import com.walterjwhite.index.modules.elasticsearch.ElasticSearchMatchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticSearchQueryBuilder {
  public QueryBuilder build(Predicate predicate) {
    if (predicate instanceof AttributePredicate) {
      return (build((AttributePredicate) predicate));
    }

    return (build((GroupPredicate) predicate));
  }

  protected QueryBuilder build(AttributePredicate attributePredicate) {
    return ElasticSearchMatchType.get(attributePredicate.getMatchType()).build(attributePredicate);
  }

  protected BoolQueryBuilder build(GroupPredicate groupPredicate) {
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    for (Predicate predicate : groupPredicate.getChildrenPredicates()) {
      if (Conjunction.And.equals(groupPredicate.getConjunction()))
        boolQueryBuilder.must().add(build(predicate));
      else boolQueryBuilder.should().add(build(predicate));
    }

    return (boolQueryBuilder);
  }
}
