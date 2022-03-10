package com.org.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MessageDto {
    private UUID id;
    private String text;
}
