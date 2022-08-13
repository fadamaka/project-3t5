package com.fada.project3t5.domain;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class Match {

    @PrimaryKey
    @Builder.Default()
    private UUID id =UUID.randomUUID();
    private Integer playerOne;
    private Integer playerTwo;
    private MatchStatus status;
    private Map<Integer,Move> moves;
}
