package com.fada.project3t5.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MatchResult {

    P1_WON_BY_5("P1 won by 5 in a row"),
    P2_WON_BY_5("P2 won by 5 in a row"),
    P1_MADE_INVALID_MOVE("P1 made invalid move so P2 won"),
    P2_MADE_INVALID_MOVE("P2 made invalid move so P1 won");

    private final String name;
}
