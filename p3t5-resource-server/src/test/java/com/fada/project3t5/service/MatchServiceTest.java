package com.fada.project3t5.service;

import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.fada.project3t5.domain.enums.Sign;
import com.fada.project3t5.domain.model.Match;
import com.fada.project3t5.domain.model.Move;
import com.fada.project3t5.domain.model.Point;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class MatchServiceTest {

    MatchService matchService = new MatchService();

    @ParameterizedTest
    @MethodSource("provideMapsForgetWinningAxis")
    void getWinningAxis(Map<Point, Move> movesMap, int expected) {
        LinkedList<Map.Entry<Point, Move>> result = matchService
                .getWinningAxis(Match.builder().movesMap(movesMap).build(), Point.of(1, 1));
        Assertions.assertEquals(expected, result.size());
    }

    private static Stream<Arguments> provideMapsForgetWinningAxis() {
        return Stream.of(
                Arguments.of(Map.of(Point.of(0, 0),
                        Move.of(1, Sign.X),
                        Point.of(1, 1),
                        Move.of(2, Sign.O),
                        Point.of(1, 2),
                        Move.of(3, Sign.O),
                        Point.of(1, 3),
                        Move.of(4, Sign.O),
                        Point.of(1, 0),
                        Move.of(5, Sign.O),
                        Point.of(1, -1),
                        Move.of(6, Sign.O),
                        Point.of(1, -3),
                        Move.of(7, Sign.O),
                        Point.of(1, -2),
                        Move.of(8, Sign.O)), 7),
                Arguments.of(Map.of(Point.of(0, 0),
                        Move.of(1, Sign.X),
                        Point.of(1, 1),
                        Move.of(2, Sign.O),
                        Point.of(-2, 1),
                        Move.of(3, Sign.O),
                        Point.of(-1, 1),
                        Move.of(4, Sign.O),
                        Point.of(2, 1),
                        Move.of(5, Sign.O),
                        Point.of(3, 1),
                        Move.of(6, Sign.O),
                        Point.of(0, 1),
                        Move.of(7, Sign.O),
                        Point.of(9, 1),
                        Move.of(8, Sign.O)), 6),
                Arguments.of(Map.of(Point.of(0, 0),
                        Move.of(1, Sign.X),
                        Point.of(1, 1),
                        Move.of(2, Sign.O),
                        Point.of(2, 2),
                        Move.of(3, Sign.O),
                        Point.of(3, 3),
                        Move.of(4, Sign.O),
                        Point.of(4, 4),
                        Move.of(5, Sign.O),
                        Point.of(-1, -1),
                        Move.of(6, Sign.O),
                        Point.of(-2, -2),
                        Move.of(7, Sign.O),
                        Point.of(5, 5),
                        Move.of(8, Sign.O)), 5),
                Arguments.of(Map.of(Point.of(0, 0),
                        Move.of(1, Sign.X),
                        Point.of(1, 1),
                        Move.of(2, Sign.O),
                        Point.of(0, 2),
                        Move.of(3, Sign.O),
                        Point.of(-1, 3),
                        Move.of(4, Sign.O),
                        Point.of(-2, 4),
                        Move.of(5, Sign.O),
                        Point.of(2, 0),
                        Move.of(6, Sign.O),
                        Point.of(4, -1),
                        Move.of(7, Sign.O),
                        Point.of(5, -2),
                        Move.of(8, Sign.O)), 5),
                Arguments.of(Map.of(Point.of(0, 0),
                        Move.of(1, Sign.X),
                        Point.of(1, 1),
                        Move.of(2, Sign.O),
                        Point.of(2, 2),
                        Move.of(3, Sign.O),
                        Point.of(7, 3),
                        Move.of(4, Sign.O),
                        Point.of(4, 4),
                        Move.of(5, Sign.O),
                        Point.of(-1, -1),
                        Move.of(6, Sign.O),
                        Point.of(-2, -2),
                        Move.of(7, Sign.O),
                        Point.of(5, 5),
                        Move.of(8, Sign.O)), 0));
    }

}