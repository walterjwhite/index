package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.api.model.query.Conjunction;
import com.walterjwhite.index.api.model.query.MatchType;
import com.walterjwhite.index.api.model.query.predicate.AttributePredicate;
import com.walterjwhite.index.api.model.query.predicate.GroupPredicate;
import com.walterjwhite.index.api.model.query.predicate.Predicate;
import com.walterjwhite.index.api.model.query.predicate.Range;
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
    if (MatchType.Equals.equals(attributePredicate.getMatchType())) {
      return (QueryBuilders.matchQuery(
          attributePredicate.getAttributePath(), attributePredicate.getArgument()));
    }
    if (MatchType.Like.equals(attributePredicate.getMatchType())) {
      return (QueryBuilders.fuzzyQuery(
          attributePredicate.getAttributePath(), attributePredicate.getArgument()));
    }
    if (MatchType.Regex.equals(attributePredicate.getMatchType())) {
      return (QueryBuilders.regexpQuery(
          attributePredicate.getAttributePath(), (String) attributePredicate.getArgument()));
    }
    if (MatchType.Range.equals(attributePredicate.getMatchType())) {
      return (QueryBuilders.rangeQuery(attributePredicate.getAttributePath())
          .gt(((Range) attributePredicate.getArgument()).getLow())
          .lt(((Range) attributePredicate.getArgument()).getHigh()));
    }

    throw (new UnsupportedOperationException(
        "Not yet supported." + attributePredicate.getMatchType()));
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
