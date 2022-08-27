package com.fada.project3t5.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.fada.project3t5.domain.Match;
@EnableScan
public interface MatchRepository extends CrudRepository<Match,Integer> {
}
