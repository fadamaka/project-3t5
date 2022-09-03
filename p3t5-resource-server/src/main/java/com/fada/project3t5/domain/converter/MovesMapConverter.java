package com.fada.project3t5.domain.converter;

import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fada.project3t5.domain.Move;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class MovesMapConverter implements AttributeConverter<Map<Integer, Move>, String> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<Integer, Move> movesMap) {

        String movesMapJson = null;
        try {
            movesMapJson = objectMapper.writeValueAsString(movesMap);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return movesMapJson;
    }

    @Override
    public Map<Integer, Move> convertToEntityAttribute(String movesMapJSON) {

        Map<Integer, Move> movesMap = null;
        try {
            movesMap = objectMapper.readValue(movesMapJSON, new TypeReference<Map<Integer, Move>>(){});
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return movesMap;
    }

}
