package com.fada.project3t5.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Player")
public class Player {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private Integer id;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute
    private String apiKey;
}
