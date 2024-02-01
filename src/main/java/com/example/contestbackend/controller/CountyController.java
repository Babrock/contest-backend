package com.example.contestbackend.controller;

import com.example.contestbackend.dto.CommunityRequest;
import com.example.contestbackend.dto.CountyRequest;
import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.model.County;
import com.example.contestbackend.model.Voivodeship;
import com.example.contestbackend.repository.CountyRepository;
import com.example.contestbackend.service.CountyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/counties")
public class CountyController {
    private final CountyService countyService;

    @GetMapping
    public List<County> getCounties() {return countyService.getCounties();}

    @GetMapping(params = "voivodeship")
    public List<DictionaryResponse> getAllCountiesByVoivodeshipId(@RequestParam Integer voivodeship) {
        return countyService.getAllByVoivodeshipId(voivodeship);
    }

    @GetMapping("/voivodeships/{voivodeships}")
    public ResponseEntity<List<DictionaryResponse>> getAllCountiesByVoivodeshipsId(@PathVariable List<Integer> voivodeships) {
        List<DictionaryResponse> counties = countyService.getAllByVoivodeshipsId(voivodeships);
        return new ResponseEntity<>(counties, HttpStatus.OK);
    }

    @PostMapping
    void add(@RequestBody List<CountyRequest>requests){
        countyService.addAll(requests);
    }

    @GetMapping("/{id}")
    public List<County> getVoivodeshipsNamesById(@PathVariable List<Integer> id){ return countyService.getCountiesNamesById(id); }


    @GetMapping("/region/{id}")
    public List<County> getCountiesByRegionId(@PathVariable Integer id){
        return countyService.getCountiesByRegionId(id);
    }
}
