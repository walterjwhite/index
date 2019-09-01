package com.walterjwhite.index.service;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.service.IndexMaintenanceService;
import com.walterjwhite.index.query.FindIndexByNameQuery;
import com.walterjwhite.infrastructure.inject.core.service.ShutdownAware;
import com.walterjwhite.infrastructure.inject.core.service.StartupAware;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Provider;
import lombok.RequiredArgsConstructor;

/**
 * 1. automatically create indexes on start (if configured) 2. automatically delete indexes on stop
 * (if configured) 3. as part of the create/delete process, also record the index details in a JPA
 * repository so it can be looked up outside of the index itself.
 */
@RequiredArgsConstructor
public abstract class AbstractIndexMaintenanceService
    implements IndexMaintenanceService, StartupAware, ShutdownAware {
  protected final Provider<Repository> repositoryProvider;
  protected final boolean createIndexesOnStart;
  protected final boolean deleteIndexesOnStop;

  @Override
  public void create(Index index) throws IOException {
    doCreate(index);
  }

  protected abstract void doCreate(Index index) throws IOException;

  /**
   * Create the "default" index.
   *
   * @throws IOException
   */
  @Override
  public void create() throws IOException {
    create(lookupDefault());
  }

  @Override
  public void delete(Index index) throws IOException {
    doDelete(index);
  }

  protected abstract void doDelete(Index index) throws IOException;

  /**
   * Delete the "default" index.
   *
   * @throws IOException
   */
  @Override
  public void delete() throws IOException {
    delete(lookupDefault());
  }

  protected Index lookupDefault() {
    //        return
    //                repositoryProvider
    //                        .get()
    //                        .query(
    //                                new FindIndexByNameQuery(
    //
    // FindIndexByNameQuery.DEFAULT_INDEX_NAME));

    return new Index(FindIndexByNameQuery.DEFAULT_INDEX_NAME);
  }

  // TODO: I believe this needs to use index name service, which I think was originally intended to
  // determine all of the index names.
  @Override
  public void onShutdown() throws Exception {
    if (deleteIndexesOnStop) {
      getIndexes()
          .forEach(
              index -> {
                try {
                  doDelete(index);
                } catch (IOException e) {
                  throw new RuntimeException("Error deleting index", e);
                }
              });
    }
  }

  // TODO: set a field/column to indicate the index is deleted
  // TODO: actually hook this up to the db
  protected Set<Index> getIndexes() {
    final Set<Index> indexes = new HashSet<>();
    indexes.add(new Index(FindIndexByNameQuery.DEFAULT_INDEX_NAME));
    return Collections.unmodifiableSet(indexes);
  }

  @Override
  public void onStartup() throws Exception {
    if (createIndexesOnStart) {
      getIndexes()
          .forEach(
              index -> {
                try {
                  doCreate(index);
                } catch (IOException e) {
                  throw new RuntimeException("Error creating index", e);
                }
              });
    }
  }
}
