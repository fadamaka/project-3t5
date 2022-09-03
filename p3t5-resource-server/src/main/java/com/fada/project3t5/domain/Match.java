package com.fada.project3t5.domain;


import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fada.project3t5.domain.converter.MovesMapConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="\"match\"")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player_one_id", foreignKey = @ForeignKey(name = "match_player_one_id_fkey"))
    private Player p1;
    @ManyToOne
    @JoinColumn(name = "player_two_id",foreignKey = @ForeignKey(name = "match_player_two_id_fkey"))
    private Player p2;
    @Column(name="status_id", columnDefinition = "BIGINTEGER")
    private MatchStatus status;
    @Column(name = "moves", columnDefinition = "TEXT")
    @Convert(converter = MovesMapConverter.class)
    private Map<Integer,Move> movesMap;
}
