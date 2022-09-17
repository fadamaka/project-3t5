package com.fada.project3t5.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MatchStatus {

    READY(0, "Ready"),
    IN_PROGRESS(1, "In Progress"),
    FINISHED(2, "Finished");

    private final int id;
    private final String name;

}
