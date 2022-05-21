package com.fada.project3t5.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MatchStatus {
    FINISHED(1,"Finished"),
    IN_PROGRESS(2, "In Progress");

    private int id;
    private String name;

}
