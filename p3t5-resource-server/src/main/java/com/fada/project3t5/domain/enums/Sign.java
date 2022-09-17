package com.fada.project3t5.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Sign {

    X(0, "X"),
    O(1, "O");

    private final int id;
    private final String sign;

}
