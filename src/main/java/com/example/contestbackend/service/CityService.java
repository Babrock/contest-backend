package com.example.contestbackend.service;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.dto.projections.CityDictionary;
import com.example.contestbackend.model.City;
import com.example.contestbackend.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getCities(){
        return cityRepository.findAll();
    }

    public List<DictionaryResponse> getCitiesByCommunityId(Integer id){
        return cityRepository.findAllByCommunityId(id);
    }
}
