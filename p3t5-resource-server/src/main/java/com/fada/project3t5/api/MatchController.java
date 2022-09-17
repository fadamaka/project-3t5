package com.fada.project3t5.api;

import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fada.project3t5.domain.enums.MatchStatus;
import com.fada.project3t5.domain.model.Match;
import com.fada.project3t5.domain.model.Player;
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

    @GetMapping(value = "/fake", produces = { "application/json" })
    public ResponseEntity<Boolean> createFakeData() {
        playerRepository.saveAll(List.of(Player.builder().name("someone").email("meh@meh.meh").build(),
                Player.builder().name("someone").email("meh2@meh.meh").build()));
        List<Player> players = playerRepository.findAll();

        matchRepository.saveAll(
                List.of(Match.builder().p1(players.get(0)).p2(players.get(1)).status(MatchStatus.FINISHED).build(),
                        Match.builder().p1(players.get(1)).p2(players.get(0)).status(MatchStatus.FINISHED).build()));
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/create", produces = { "application/json" })
    public ResponseEntity<Match> createNew(@Email String email) {
        return ResponseEntity.ok(matchService.createNew(email));
    }
}
