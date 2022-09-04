package com.fada.project3t5.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fada.project3t5.domain.Player;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("players")
public class PlayerController {

    public static final String ENTRY_URL = "http://localhost:8083/auth/realms/p3t5/protocol/openid-connect/auth?response_type=code&client_id=jwtClient&scope=read&redirect_uri=http://localhost:8081/players/get";
    public static final String TOKEN_URL = "http://localhost:8083/auth/realms/p3t5/protocol/openid-connect/token";
    public static final String REDIRECT_URL = "http://localhost:8081/players/get";

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
            value = "/gets",
            produces = { "application/json" }
    )
    public ResponseEntity<String> playerGet(JwtAuthenticationToken principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok().body(principal.getToken().getTokenValue());
    }

    @GetMapping(value = "/get")
    public ResponseEntity<String> getToken(@RequestParam String code){
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("client_id", "jwtClient");
        params.put("redirect_uri", REDIRECT_URL);
        params.put("client_secret", "jwtClientSecret");
        Response response = RestAssured
                .given()
                .formParams(params)
                .post(TOKEN_URL);
        return ResponseEntity.ok().body(response
                .jsonPath()
                .getString("access_token"));
    }
}
