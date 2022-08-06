package com.fada.project3t5.domain;


import java.util.List;

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
    private Integer id;
    private Integer playerOne;
    private Integer playerTwo;
    private MatchStatus status;
    private List<Integer> moves;
}
