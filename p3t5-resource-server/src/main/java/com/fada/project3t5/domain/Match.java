package com.fada.project3t5.domain;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Node
public class Match {

    @Id
    @GeneratedValue
    private Long id;
    @Relationship(type = "PLAYED_AS_P1", direction = Relationship.Direction.INCOMING)
    @EqualsAndHashCode.Exclude
    public Player p1;
    @Relationship(type = "PLAYED_AS_P2", direction = Relationship.Direction.INCOMING)
    @EqualsAndHashCode.Exclude
    public Player p2;
    private MatchStatus status;
    private String moves;
}
