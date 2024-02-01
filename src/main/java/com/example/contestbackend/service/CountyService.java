package com.example.contestbackend.service;

import com.example.contestbackend.dto.CountyRequest;
import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.model.County;
import com.example.contestbackend.model.Voivodeship;
import com.example.contestbackend.repository.CountyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountyService {
    private final CountyRepository countyRepository;

    private final VoivodeshipService voivodeshipService;

    public List<County> getCounties (){return countyRepository.findAll();}

    public County getCountyByName(String countyName) {
        return countyRepository.findByName(countyName);
    }

    public List<DictionaryResponse> getAllByVoivodeshipId(Integer voivodeship) {
        return countyRepository.findAllByVoivodeshipId(voivodeship);
    }

    public List<DictionaryResponse> getAllByVoivodeshipsId(List <Integer> voivodeship) {
        return countyRepository.findAllByVoivodeshipIdIn(voivodeship);
    }

    public void addAll(List<CountyRequest> requests) {
        requests.forEach(countyRequest -> {
            addIfNotExists(countyRequest);
        });
    }

    public void addIfNotExists(CountyRequest countyRequest){
        if (!countyRepository.existsByName(countyRequest.getCountyName())){
            Voivodeship voivodeship = voivodeshipService.getVoivodeshipByName(countyRequest.getVoivodeshipName());
            if (voivodeship == null) System.out.println(countyRequest.getVoivodeshipName());
            County county = new County(countyRequest.getCountyName(),voivodeship);
            countyRepository.save(county);
        }
    }

    public List<County> getCountiesNamesById(List<Integer> id){
        return countyRepository.findAllByIdIn(id);
    }


    public List<County> getCountiesByRegionId(Integer id){
        return countyRepository.findByRegionId(id);
    }
}
