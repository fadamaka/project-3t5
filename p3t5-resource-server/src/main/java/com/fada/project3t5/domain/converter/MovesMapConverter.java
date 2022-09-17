package com.fada.project3t5.domain.converter;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fada.project3t5.domain.model.Move;
import com.fada.project3t5.domain.model.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class MovesMapConverter implements AttributeConverter<Map<Point, Move>, String> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<Point, Move> movesMap) {

        Map<String, Move> map = movesMap.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> "[" + e.getKey().x() + "," + e.getKey().y() + "]", Map.Entry::getValue));

        String movesMapJson = null;
        try {
            movesMapJson = objectMapper.writeValueAsString(map);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return movesMapJson;
    }

    @Override
    public Map<Point, Move> convertToEntityAttribute(String movesMapJSON) {

        Map<Point, Move> movesMap = null;
        try {
            movesMap = objectMapper.readValue(movesMapJSON, new TypeReference<Map<String, Move>>() {
            }).entrySet().stream().collect(Collectors.toMap(e -> {
                try {
                    return Point.fromArray(objectMapper.readValue(e.getKey(), int[].class));
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }, Map.Entry::getValue));
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return movesMap;
    }

}
