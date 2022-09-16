package com.fada.project3t5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fada.project3t5.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByEmail(String email);

}
