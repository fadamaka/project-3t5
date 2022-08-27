package com.fada.project3t5.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.fada.project3t5.domain.Player;

@EnableScan
public interface PlayerRepository extends CrudRepository<Player,Integer> {
}
