package com.fada.project3t5.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fada.project3t5.domain.dto.MoveDTO;
import com.fada.project3t5.domain.enums.MatchStatus;
import com.fada.project3t5.domain.enums.Sign;
import com.fada.project3t5.domain.model.Match;
import com.fada.project3t5.domain.model.Move;
import com.fada.project3t5.domain.model.Player;
import com.fada.project3t5.domain.model.Point;
import com.fada.project3t5.repository.MatchRepository;
import com.fada.project3t5.repository.PlayerRepository;
import com.fada.project3t5.service.MatchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("matches")
public class MatchController {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    MatchService matchService;

    /**
     * GET /matches Returns all matches from the system that the user has access to
     *
     * @return A list of matches. (status code 200)
     */
    @ApiOperation(value = "", nickname = "matchesGet", notes = "Returns all matches from the system that the user has access to", response = Match.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A list of matches.", response = Match.class, responseContainer = "List") })
    @GetMapping(value = "/findAll", produces = { "application/json" })
    public ResponseEntity<List<Match>> matchesGet() {
        List<Match> matches = matchRepository.findAll();
        if (matches.size() > 0) {
            return ResponseEntity.ok(matches);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    /**
     * GET /matches/{id} Returns with a match
     *
     * @return A single match. (status code 200)
     */
    @ApiOperation(value = "", nickname = "findOne", notes = "Returns with a match", response = Match.class, responseContainer = "Match", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A single match.", response = Match.class, responseContainer = "Match") })
    @GetMapping(value = "/{id}", produces = { "application/json" })
    public ResponseEntity<Match> findOne(@PathVariable Long id) {
        Optional<Match> match = matchRepository.findById(id);
        if (match.isPresent()) {
            return ResponseEntity.ok(match.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/fake", produces = { "application/json" })
    public ResponseEntity<Boolean> createFakeData() {
        List<Player> players = playerRepository.findAll();

        matchRepository.saveAll(List.of(
                Match.builder()
                        .p1(players.get(0))
                        .p2(players.get(1))
                        .status(MatchStatus.P1_WON)
                        .movesMap(Map.of(Point.of(0, 0),
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
                                Move.of(8, Sign.O)))
                        .build(),
                Match.builder()
                        .p1(players.get(1))
                        .p2(players.get(0))
                        .status(MatchStatus.P1_WON)
                        .movesMap(Map.of(Point.of(0, 0),
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
                                Move.of(8, Sign.O)))
                        .build()));
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/create", produces = { "application/json" })
    public ResponseEntity<Match> createNew(String email) {
        return ResponseEntity.ok(matchService.createNew(email));
    }

    @PostMapping(value = "/{matchId}/move", consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity<Match> move(@PathVariable Long matchId, @RequestBody MoveDTO moveDTO) {
        return ResponseEntity.ok(matchService.applyMove(matchId, moveDTO));
    }
}
