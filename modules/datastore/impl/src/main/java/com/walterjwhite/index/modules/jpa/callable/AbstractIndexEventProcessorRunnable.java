package com.walterjwhite.index.modules.jpa.callable;

import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.queue.event.model.QueuedEvent;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import java.time.Duration;

public abstract class AbstractIndexEventProcessorRunnable extends AbstractRunnable
    implements TimeConstrainedMethodInvocation {

  protected final IndexService indexService;
  // timeout for the actual indexing activity
  //  protected final ChronoUnit timeoutUnits;
  //  protected final long timeoutValue;

  public AbstractIndexEventProcessorRunnable(IndexService indexService) {
    super();
    this.indexService = indexService;
  }

  protected IndexableRecord indexableRecord;

  // TODO: which index do we load these records into?
  // TODO: this should be configured
  protected Index index = new Index("default");

  @TimeConstrained
  // (timeoutUnits = IndexTimeoutUnits.class, timeoutValue = IndexTimeoutValue.class)
  protected void doCall() throws Exception {
    // TODO: should IndexableRecord be just (Index, EntityReference?)
    //  protected Index index;
    //  protected EntityType entityType;
    //  protected String id;
    //  protected AbstractEntity entity;

    indexableRecord =
        new IndexableRecord(
            index, ((QueuedEvent) currentJobExecution.getQueued()).getEntityReference(), null);
    doIndex();
  }

  protected abstract IndexActivity doIndex() throws Exception;

  @Override
  public Duration getAllowedExecutionDuration() {
    // return Duration.of(timeoutValue, timeoutUnits);
    return null;
  }
}
