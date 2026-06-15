package com.org.graphql.scheduler.base;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import org.springframework.util.StopWatch;

/**
 * Template Method pattern: defines the execution skeleton; subclasses implement performTask().
 */
@Slf4j
public abstract class AbstractScheduler {

    public void executeScheduledTask() {
        LockAssert.assertLocked();

        String taskName = getClass().getSimpleName();
        StopWatch sw = new StopWatch(taskName);
        sw.start(taskName);
        log.info("[{}] Starting scheduled task", taskName);

        try {
            performTask();
            sw.stop();
            log.info("[{}] Completed in {} ms", taskName, sw.getLastTaskTimeMillis());
        } catch (Exception ex) {
            sw.stop();
            log.error("[{}] Failed after {} ms: {}", taskName, sw.getLastTaskTimeMillis(), ex.getMessage(), ex);
            throw ex;
        }
    }

    protected abstract void performTask();
}
