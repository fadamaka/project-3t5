package com.fada.project3t5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fada.project3t5.domain.Player;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
