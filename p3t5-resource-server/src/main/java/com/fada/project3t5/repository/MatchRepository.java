package com.fada.project3t5.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.fada.project3t5.domain.Match;

public interface MatchRepository extends Neo4jRepository<Match,Long> {
}
