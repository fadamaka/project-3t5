package com.fada.project3t5.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MatchStatus {

    READY(0, "Ready"),
    IN_PROGRESS(1, "In Progress"),
    P1_WON(2, "Player 1 won"),
    P2_WON(3, "Player 2 won");

    private final int id;
    private final String name;

}
