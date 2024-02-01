package com.example.contestbackend.dto;

import com.example.contestbackend.model.Region;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class FormResponse {
    private Integer id;
    private Instant uploadDate;
    private Region regionId;
    private String combinedInfo;

    public FormResponse(Integer id, Instant uploadDate, Region regionId) {
        this.id = id;
        this.uploadDate = uploadDate;
        this.regionId = regionId;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        this.combinedInfo = String.format("%d_%s_%d", id, uploadDate.atZone(ZoneId.systemDefault()).format(formatter), regionId.getId()).replace("_", "-");;
    }

}
