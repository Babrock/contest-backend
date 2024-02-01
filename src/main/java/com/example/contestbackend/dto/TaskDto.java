package com.example.contestbackend.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TaskDto {
    private int school;
    private int schoolClass;
    private String name;
    private BigDecimal score;
}
