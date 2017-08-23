package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ComplaintFiledEvent {
    private final String id;
    private final String company;
    private final String description;
}
