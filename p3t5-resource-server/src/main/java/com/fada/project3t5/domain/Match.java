package com.fada.project3t5.domain;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "match_player_one_id_fkey"))
    private Player playerOne;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "match_player_two_id_fkey"))
    private Player playerTwo;
    @Column(name="status_id")
    private MatchStatus status;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Move> moves;
}
