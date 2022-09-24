package com.fada.project3t5.domain.model;

import com.fada.project3t5.domain.enums.Sign;

public record Move(Integer id, Sign sign) {

    public static Move of(Integer id, Sign sign) {
        return new Move(id, sign);
    }
}
