package com.org.graphql.strategy;

import com.org.graphql.entity.Subject;
import com.org.graphql.enums.SubjectEnum;

/**
 * Strategy pattern: pluggable subject filtering behaviour.
 * Use {@link #of(SubjectEnum)} as a factory to obtain the right strategy.
 */
@FunctionalInterface
public interface SubjectFilterStrategy {

    boolean test(Subject subject);

    static SubjectFilterStrategy of(SubjectEnum subjectType) {
        if (subjectType == null || subjectType == SubjectEnum.All) {
            return subject -> true;
        }
        return subject -> subject.getSubjectName().equals(subjectType.name());
    }
}
