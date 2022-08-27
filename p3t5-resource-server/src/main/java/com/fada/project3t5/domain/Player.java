package com.fada.project3t5.domain;

import java.util.Set;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Player {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String apiKey;

    @Relationship(type = "PLAYED_AS_P1", direction = Relationship.Direction.OUTGOING)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    public Set<Match> p1Matches;

    @Relationship(type = "PLAYED_AS_P2", direction = Relationship.Direction.OUTGOING)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    public Set<Match> p2Matches;

}
