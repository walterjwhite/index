package com.walterjwhite.index.api.service;

import com.google.inject.persist.Transactional;
import com.walterjwhite.datastore.criteria.AbstractRepository;
import com.walterjwhite.datastore.criteria.CriteriaQueryConfiguration;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.Index_;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class IndexRepository extends AbstractRepository<Index> {

  public static final String DEFAULT_INDEX_NAME = "default";

  @Inject
  public IndexRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super(entityManager, criteriaBuilder, Index.class);
  }

  @Transactional
  public Index getDefault() {
    try {
      return (findByName(DEFAULT_INDEX_NAME));
    } catch (NoResultException e) {
      return (persist(new Index(DEFAULT_INDEX_NAME)));
    }
  }

  public Index findByName(final String name) {
    final CriteriaQueryConfiguration<Index> resourceTypeCriteriaQueryConfiguration =
        getCriteriaQuery();

    Predicate indexNameCondition =
        criteriaBuilder.equal(
            resourceTypeCriteriaQueryConfiguration.getRoot().get(Index_.name), name);

    resourceTypeCriteriaQueryConfiguration.getCriteriaQuery().where(indexNameCondition);

    return (entityManager
        .createQuery(resourceTypeCriteriaQueryConfiguration.getCriteriaQuery())
        .getSingleResult());
  }
}
