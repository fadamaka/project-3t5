package com.fada.project3t5.service;

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
}
