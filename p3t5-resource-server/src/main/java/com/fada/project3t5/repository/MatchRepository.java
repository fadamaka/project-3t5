package com.fada.project3t5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fada.project3t5.domain.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
