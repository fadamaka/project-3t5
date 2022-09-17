package com.fada.project3t5.domain.model;

import java.util.Map;
import java.util.stream.Collectors;

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
import com.fada.project3t5.domain.enums.MatchStatus;
import com.fada.project3t5.domain.enums.Sign;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"match\"")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player_one_id", foreignKey = @ForeignKey(name = "match_player_one_id_fkey"))
    private Player p1;
    @ManyToOne
    @JoinColumn(name = "player_two_id", foreignKey = @ForeignKey(name = "match_player_two_id_fkey"))
    private Player p2;
    @Column(name = "status_id", columnDefinition = "BIGINTEGER")
    private MatchStatus status;
    @Column(name = "moves", columnDefinition = "TEXT")
    @Convert(converter = MovesMapConverter.class)
    private Map<Point, Move> movesMap;

    @JsonProperty
    public Map<String, Move> getMovesMap() {
        if (this.movesMap != null) {
            return movesMap.entrySet()
                    .stream()
                    .collect(Collectors.toMap(e -> "[" + e.getKey().x() + "," + e.getKey().y() + "]",
                            Map.Entry::getValue));
        }
        return Map.of();
    }

    public Sign getPlayerSign(String email) {
        if (this.p1.getEmail().equals(email)) {
            return Sign.X;
        } else if (this.p2.getEmail().equals(email)) {
            return Sign.O;
        }
        return null;
    }
}
