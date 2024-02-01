package com.example.contestbackend.controller;

import com.example.contestbackend.dto.RegionDetailsDTO;
import com.example.contestbackend.dto.RegionDto;
import com.example.contestbackend.model.Region;
import com.example.contestbackend.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionController {
    private final RegionService regionService;
    @GetMapping
    public List<RegionDetailsDTO> getAll(){
        return regionService.getRegions();
    }

    @GetMapping("/{id}")
    public Region getRegion(@PathVariable  Integer id){ return regionService.getRegion(id);}

    @PostMapping
    public void createRegion(@RequestBody RegionDto regionDto){
        regionService.createRegion(regionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRegion(@PathVariable Integer id){ regionService.deleteRegion(id);}

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable Integer id, @RequestBody RegionDto regionDto) {
        Region region = regionService.updateRegion(id, regionDto);
        return ResponseEntity.ok(region);
    }


}
