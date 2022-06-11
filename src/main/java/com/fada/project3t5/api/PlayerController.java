package com.fada.project3t5.api;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fada.project3t5.domain.Player;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("players")
public class PlayerController {

    /**
     * GET /matches
     * Returns all matches from the system that the user has access to
     *
     * @return A list of matches. (status code 200)
     */
    @ApiOperation(value = "", nickname = "playerGet", notes = "Returns information about the logged in user", response = Player.class, responseContainer = "Object", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Player info.", response = Player.class, responseContainer = "Object") })
    @GetMapping(
            value = "/get",
            produces = { "application/json" }
    )
    public ResponseEntity<String> playerGet(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok().body(currentPrincipalName);
    }
}
