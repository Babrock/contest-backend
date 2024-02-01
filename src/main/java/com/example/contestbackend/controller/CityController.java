package com.example.contestbackend.controller;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.dto.projections.CityDictionary;
import com.example.contestbackend.model.City;
import com.example.contestbackend.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    @GetMapping
    public List<City> getAll(){
        return cityService.getCities();
    }

    @GetMapping(params = "community")
    public List<DictionaryResponse> getAllByCommunityId(@RequestParam Integer community){
        return cityService.getCitiesByCommunityId(community);
    }
}
