package com.fada.project3t5.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fada.project3t5.domain.Match;
import com.fada.project3t5.domain.MatchStatus;
import com.fada.project3t5.domain.Move;
import com.fada.project3t5.domain.Player;
import com.fada.project3t5.repository.MatchRepository;
import com.fada.project3t5.repository.PlayerRepository;

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

    /**
     * GET /matches
     * Returns all matches from the system that the user has access to
     *
     * @return A list of matches. (status code 200)
     */
    @ApiOperation(value = "", nickname = "matchesGet", notes = "Returns all matches from the system that the user has access to", response = Match.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A list of matches.", response = Match.class, responseContainer = "List")})
    @GetMapping(
            value = "/findAll",
            produces = {"application/json"}
    )
    public ResponseEntity<Match> matchesGet() {
        List<Match> matches = matchRepository.findAll();
        if (matches.size() > 0) {
            return ResponseEntity.ok(matches.get(0));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(
            value = "/fake",
            produces = {"application/json"}
    )
    public ResponseEntity<Boolean> createFakeData() {
        playerRepository.saveAll(List.of(Player.builder().name("vki").apiKey("meh").build(), Player.builder().name("vkimas").apiKey("meh2").build()));
        List<Player> players = playerRepository.findAll();

        matchRepository.saveAll(List.of(Match.builder().p1(players.get(0)).p2(players.get(1)).status(MatchStatus.FINISHED).movesMap(Map.of(1,new Move("x",1,2),2,new Move("o",1,3))).build(),
                Match.builder().p1(players.get(1)).p2(players.get(0)).status(MatchStatus.FINISHED).movesMap(Map.of(1,new Move("x",1,2),2,new Move("o",1,3))).build()));
        return ResponseEntity.ok(true);
    }
}
