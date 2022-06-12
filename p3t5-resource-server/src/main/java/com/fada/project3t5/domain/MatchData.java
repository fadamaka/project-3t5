package com.fada.project3t5.domain;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
@MappedSuperclass
public class MatchData {

    String text;

}
