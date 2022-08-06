package com.fada.project3t5.repository;

import org.springframework.data.repository.CrudRepository;

import com.fada.project3t5.domain.Player;

public interface PlayerRepository extends CrudRepository<Player,Integer> {
}
