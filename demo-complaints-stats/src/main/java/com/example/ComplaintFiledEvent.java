package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ComplaintFiledEvent {
    private String id;
    private String company;
    private String description;
}
