package com.fada.project3t5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fada.project3t5.domain.model.Player;
import com.fada.project3t5.repository.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public Player getLoggedInPlayer() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = jwt.getClaim("preferred_username");

        return playerRepository.findByEmail(email)
                .orElseGet(
                        () -> playerRepository.save(Player.builder().email(email).name(jwt.getClaim("name")).build()));
    }

    public Player getPlayerByEmail(String email) {
        return playerRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enemy player not found"));
    }
}
