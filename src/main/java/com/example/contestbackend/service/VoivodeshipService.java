package com.example.contestbackend.service;

import com.example.contestbackend.model.Voivodeship;
import com.example.contestbackend.repository.VoivodeshipRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VoivodeshipService {
    private final VoivodeshipRepository voivodeshipRepository;
    private final ObjectMapper objectMapper;
    private final Map<Integer, String> voivodeshipIdToName; // This map should be populated with your data

    public List<Voivodeship> getVoivodeships(){
        return voivodeshipRepository.findAll();
    }

    public Voivodeship getVoivodeshipByName(String voivodeshipName) {
        return voivodeshipRepository.findByName(voivodeshipName);
    }

    public List<Voivodeship> getVoivodeshipsNamesById(List<Integer> id){
        return voivodeshipRepository.findAllByIdIn(id);
    }

}
