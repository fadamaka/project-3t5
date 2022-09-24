package com.fada.project3t5.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fada.project3t5.domain.dto.MoveDTO;
import com.fada.project3t5.domain.enums.MatchResult;
import com.fada.project3t5.domain.enums.MatchStatus;
import com.fada.project3t5.domain.enums.Sign;
import com.fada.project3t5.domain.model.Match;
import com.fada.project3t5.domain.model.Move;
import com.fada.project3t5.domain.model.Player;
import com.fada.project3t5.domain.model.Point;
import com.fada.project3t5.repository.MatchRepository;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerService playerService;

    private static final Integer numInRow = 5;

    public Match createNew(String opponentEmail) {
        Player p1 = playerService.getLoggedInPlayer();
        Player p2 = playerService.getPlayerByEmail(opponentEmail);

        return matchRepository.save(Match.builder()
                .p1(p1)
                .p2(p2)
                .status(MatchStatus.IN_PROGRESS)
                .movesMap(Map.of(new Point(0, 0), new Move(1, Sign.X)))
                .build());
    }

    public Match applyMove(Long matchId, MoveDTO moveDTO) {
        Player player = playerService.getLoggedInPlayer();

        Optional<Match> matchOptional = matchRepository.findById(matchId);

        if (matchOptional.isPresent() && matchOptional.get().getPlayerSign(player.getEmail()) != null
                && matchOptional.get().getStatus().equals(MatchStatus.IN_PROGRESS)) {
            Match match = matchOptional.get();
            if (match.whosTurn().getId().equals(player.getId())) {
                Point point = Point.fromMoveDTO(moveDTO);
                if (!match.getMovesMap().containsKey(point)) {
                    match.getMovesMap()
                            .put(point,
                                    new Move(match.getMovesMap().size() + 1, match.getPlayerSign(player.getEmail())));
                } else {
                    boolean who = match.getMovesMap().size() % 2 == 0;
                    match.setStatus(who ? MatchStatus.P2_WON : MatchStatus.P1_WON);
                    match.setResult(who ? MatchResult.P1_MADE_INVALID_MOVE : MatchResult.P2_MADE_INVALID_MOVE);
                }
                return matchRepository.save(match);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not the player's turn");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }
    }

    public LinkedList<Map.Entry<Point, Move>> getWinningAxis(Match match, Point lastMove) {
        Map<Point, Move> movesMap = match.getMovesMap();
        LinkedList<Map.Entry<Point, Move>> axisY = new LinkedList<Map.Entry<Point, Move>>(
                List.of(Map.entry(lastMove, movesMap.get(lastMove))));

        LinkedList<Map.Entry<Point, Move>> axisX = new LinkedList<Map.Entry<Point, Move>>(
                List.of(Map.entry(lastMove, movesMap.get(lastMove))));

        LinkedList<Map.Entry<Point, Move>> axisZ = new LinkedList<Map.Entry<Point, Move>>(
                List.of(Map.entry(lastMove, movesMap.get(lastMove))));

        LinkedList<Map.Entry<Point, Move>> axisZi = new LinkedList<Map.Entry<Point, Move>>(
                List.of(Map.entry(lastMove, movesMap.get(lastMove))));

        for (int i = 1; i < 5; i++) {
            Point nextPoint = Point.of(lastMove.x(), lastMove.y() + i);
            if (movesMap.containsKey(nextPoint) && movesMap.get(nextPoint).sign() == movesMap.get(lastMove).sign()) {
                axisY.addLast(Map.entry(nextPoint, movesMap.get(nextPoint)));
            } else {
                break;
            }
        }

        for (int i = 1; i < 5; i++) {
            Point nextPoint = Point.of(lastMove.x(), lastMove.y() - i);
            if (movesMap.containsKey(nextPoint) && movesMap.get(nextPoint).sign() == movesMap.get(lastMove).sign()) {
                axisY.addFirst(Map.entry(nextPoint, movesMap.get(nextPoint)));
            } else {
                break;
            }
        }

        if (axisY.size() >= 5) {
            return axisY;
        }

        for (int i = 1; i < 5; i++) {
            Point nextPoint = Point.of(lastMove.x() + i, lastMove.y());
            if (movesMap.containsKey(nextPoint) && movesMap.get(nextPoint).sign() == movesMap.get(lastMove).sign()) {
                axisX.addLast(Map.entry(nextPoint, movesMap.get(nextPoint)));
            } else {
                break;
            }
        }

        for (int i = 1; i < 5; i++) {
            Point nextPoint = Point.of(lastMove.x() - i, lastMove.y());
            if (movesMap.containsKey(nextPoint) && movesMap.get(nextPoint).sign() == movesMap.get(lastMove).sign()) {
                axisX.addFirst(Map.entry(nextPoint, movesMap.get(nextPoint)));
            } else {
                break;
            }
        }

        if (axisX.size() >= 5) {
            return axisX;
        }

        for (int i = 1; i < 5; i++) {
            Point nextPoint = Point.of(lastMove.x() + i, lastMove.y() + i);
            if (movesMap.containsKey(nextPoint) && movesMap.get(nextPoint).sign() == movesMap.get(lastMove).sign()) {
                axisZ.addLast(Map.entry(nextPoint, movesMap.get(nextPoint)));
            } else {
                break;
            }
        }

        for (int i = 1; i < 5; i++) {
            Point nextPoint = Point.of(lastMove.x() - i, lastMove.y() - i);
            if (movesMap.containsKey(nextPoint) && movesMap.get(nextPoint).sign() == movesMap.get(lastMove).sign()) {
                axisZ.addFirst(Map.entry(nextPoint, movesMap.get(nextPoint)));
            } else {
                break;
            }
        }

        if (axisZ.size() >= 5) {
            return axisZ;
        }

        for (int i = 1; i < 5; i++) {
            Point nextPoint = Point.of(lastMove.x() - i, lastMove.y() + i);
            if (movesMap.containsKey(nextPoint) && movesMap.get(nextPoint).sign() == movesMap.get(lastMove).sign()) {
                axisZi.addLast(Map.entry(nextPoint, movesMap.get(nextPoint)));
            } else {
                break;
            }
        }

        for (int i = 1; i < 5; i++) {
            Point nextPoint = Point.of(lastMove.x() + i, lastMove.y() - i);
            if (movesMap.containsKey(nextPoint) && movesMap.get(nextPoint).sign() == movesMap.get(lastMove).sign()) {
                axisZi.addFirst(Map.entry(nextPoint, movesMap.get(nextPoint)));
            } else {
                break;
            }
        }

        if (axisZi.size() >= 5) {
            return axisZi;
        }

        return new LinkedList<Map.Entry<Point, Move>>(List.of());
    }
}
