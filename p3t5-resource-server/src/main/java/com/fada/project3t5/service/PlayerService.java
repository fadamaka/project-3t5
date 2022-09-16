package com.fada.project3t5.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.fada.project3t5.domain.Player;
import com.fada.project3t5.repository.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public Player getLoggedInPlayer() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = jwt.getClaim("preferred_username");

        Optional<Player> player = playerRepository.findByEmail(email);

        return player.orElseGet(
                () -> playerRepository.save(Player.builder().email(email).name(jwt.getClaim("name")).build()));
    }
}
