package com.example.contestbackend.dto;

import com.example.contestbackend.model.Region;
import com.example.contestbackend.model.County;
import com.example.contestbackend.model.Voivodeship;
import lombok.Data;

import java.util.List;

@Data
public class RegionDetailsDTO {
    private Integer id;
    private String name;
    private List<String> voivodeships;
    private List<String> counties;

    public RegionDetailsDTO(Region region, List<String> voivodeships, List<String> counties) {
        this.id = region.getId();
        this.name = region.getName();
        this.voivodeships = voivodeships;
        this.counties = counties;
    }


}
