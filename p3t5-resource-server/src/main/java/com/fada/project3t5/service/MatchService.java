package com.fada.project3t5.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
