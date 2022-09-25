package com.fada.project3t5.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
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
import com.fada.project3t5.repository.PlayerRepository;
import com.fada.project3t5.service.PlayerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MatchControllerITest {

    @MockBean
    PlayerService playerService;
    @Autowired
    MatchController matchController;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PlayerRepository playerRepository;

    Player p1 = Player.builder().name("one").email("one@one.one").build();

    Player p2 = Player.builder().name("two").email("two@two.two").build();
    Match match = Match.builder()
            .p1(p1)
            .p2(p2)
            .status(MatchStatus.IN_PROGRESS)
            .movesMap(Map.of(Point.of(0, 0),
                    Move.of(1, Sign.X),
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
                    Move.of(8, Sign.O)))
            .build();

    @BeforeEach
    public void setup() {
        playerRepository.saveAll(List.of(p1, p2));
        matchRepository.save(match);
        Mockito.when(playerService.getLoggedInPlayer()).thenReturn(p2);
    }

    @Test
    public void winTest() {
        Match testMatch = matchController.move(this.getMatchId(), new MoveDTO(1, 1)).getBody();
        assertEquals(MatchStatus.P2_WON, testMatch.getStatus());
        assertEquals(MatchResult.P2_WON_BY_5, testMatch.getResult());
    }

    @Test
    public void invalidMoveTest() {
        Match testMatch = matchController.move(this.getMatchId(), new MoveDTO(0, 0)).getBody();
        assertEquals(MatchStatus.P1_WON, testMatch.getStatus());
        assertEquals(MatchResult.P2_MADE_INVALID_MOVE, testMatch.getResult());
    }

    @Test
    public void notThePlayersTurnTest() {
        matchController.move(this.getMatchId(), new MoveDTO(10, 10));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> matchController.move(this.getMatchId(), new MoveDTO(10, 10)));
        assertEquals("Not the player's turn", exception.getReason());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    public void matchNotFoundTest() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> matchController.move(11111L, new MoveDTO(10, 10)));
        assertEquals("Match not found", exception.getReason());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @AfterEach
    public void drop() {
        matchRepository.deleteAll();
        playerRepository.deleteAll();
    }

    public Long getMatchId() {
        Optional<Long> matchId = matchRepository.findAll().stream().map(Match::getId).findFirst();
        assertTrue(matchId.isPresent());
        return matchId.get();
    }

}