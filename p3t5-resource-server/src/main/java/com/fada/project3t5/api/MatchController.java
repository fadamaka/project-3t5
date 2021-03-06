package com.fada.project3t5.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.fada.project3t5.domain.Match;
import com.fada.project3t5.repository.MatchRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("matches")
public class MatchController {

    @Autowired
    MatchRepository matchRepository;

    /**
     * GET /matches
     * Returns all matches from the system that the user has access to
     *
     * @return A list of matches. (status code 200)
     */
    @ApiOperation(value = "", nickname = "matchesGet", notes = "Returns all matches from the system that the user has access to", response = Match.class, responseContainer = "List", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A list of matches.", response = Match.class, responseContainer = "List") })
    @GetMapping(
            value = "/findAll",
            produces = { "application/json" }
    )
    public ResponseEntity<List<Match>> matchesGet() {
        List<Match> matches = matchRepository.findAll();
        if(matches.size()>0){
            return ResponseEntity.ok(matches);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
