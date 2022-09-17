package com.fada.project3t5.domain.model;

public record Point(int x, int y) {

    public static Point fromArray(int[] array) {
        return new Point(array[0], array[1]);
    }
}
