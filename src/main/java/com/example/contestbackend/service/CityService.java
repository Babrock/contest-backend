package com.example.contestbackend.service;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.dto.projections.CityDictionary;
import com.example.contestbackend.model.City;
import com.example.contestbackend.model.Community;
import com.example.contestbackend.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    public City getCity(String cityName){
        return cityRepository.findByName(cityName);
    }

    public List<City> getCities(){
        return cityRepository.findAll();
    }

    public City getCityByNameAndCommunityId(String name, Integer id){
        return cityRepository.findByNameAndCommunityId(name , id);
    }

    public List<DictionaryResponse> getCitiesByCommunityId(Integer id){
        return cityRepository.findAllByCommunityId(id);
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

}
