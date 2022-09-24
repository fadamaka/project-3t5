package com.fada.project3t5.domain.model;

import com.fada.project3t5.domain.dto.MoveDTO;

public record Point(int x, int y) {

    public static Point fromArray(int[] array) {
        return new Point(array[0], array[1]);
    }

    public static Point fromMoveDTO(MoveDTO moveDTO) {
        return new Point(moveDTO.x(), moveDTO.y());
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}
