package com.example.contestbackend.dto;

import com.example.contestbackend.model.County;
import com.example.contestbackend.model.Voivodeship;
import lombok.Getter;

import java.util.List;

@Getter
public class RegionDto {
    private String name;
    private List<Integer> countiesIds;
}
