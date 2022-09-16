package com.fada.project3t5.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MatchStatus {

    READY(0, "Ready"),
    IN_PROGRESS(1, "In Progress"),
    FINISHED(2, "Finished");

    private int id;
    private String name;

}
