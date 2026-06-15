package com.org.graphql.scheduler;

import com.org.graphql.repository.StudentRepository;
import com.org.graphql.scheduler.base.AbstractScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentReportScheduler extends AbstractScheduler {

    private final StudentRepository studentRepository;

    @Scheduled(cron = "${shedlock.student-report.cron:0 */5 * * * *}")
    @SchedulerLock(
            name = "studentReportScheduler",
            lockAtMostFor = "${shedlock.student-report.lock-at-most-for:30s}",
            lockAtLeastFor = "${shedlock.student-report.lock-at-least-for:10s}"
    )
    public void run() {
        executeScheduledTask();
    }

    @Override
    protected void performTask() {
        long count = studentRepository.count();
        log.info("Student report: total students in database = {}", count);
    }
}
