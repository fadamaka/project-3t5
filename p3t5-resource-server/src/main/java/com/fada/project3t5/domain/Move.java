package com.fada.project3t5.domain;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType
public record Move(String sign, Integer x, Integer y) {
}
