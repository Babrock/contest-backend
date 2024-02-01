package com.example.contestbackend.service;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.dto.NameResponse;
import com.example.contestbackend.dto.RegionDetailsDTO;
import com.example.contestbackend.dto.RegionDto;
import com.example.contestbackend.model.County;
import com.example.contestbackend.model.Region;
import com.example.contestbackend.model.Voivodeship;
import com.example.contestbackend.repository.CountyRepository;
import com.example.contestbackend.repository.RegionRepository;
import com.example.contestbackend.repository.VoivodeshipRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final CountyRepository countyRepository;

    public List<RegionDetailsDTO> getRegions() {
        List<Region> regions = regionRepository.findAll();
        List<RegionDetailsDTO> regionDetailsList = new ArrayList<>();
        for (Region region : regions) {
            List<County> counties = countyRepository.findByRegion(region);
            List<String> countyNamesList = counties.stream()
                    .map(county -> new String(  county.getName() + " "))
                    .collect(Collectors.toList());
            List<String> voivodeshipDetailsList = counties.stream()
                    .map(county -> new String( county.getVoivodeship().getName()))
                    .distinct()
                    .collect(Collectors.toList());
            regionDetailsList.add(new RegionDetailsDTO(region, voivodeshipDetailsList, countyNamesList));
        }
        return regionDetailsList;
    }

    public Region getRegion(Integer id){
        return regionRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteRegion(Integer id) {
        List<County> counties = countyRepository.findByRegionId(id);
        counties.forEach(county -> {
            county.setRegion(null);
        });
        regionRepository.deleteById(id);
    }

    public Region createRegion(RegionDto regionDto) {
        Region region = new Region();
        region.setName(regionDto.getName());
        try {
            Region savedRegion = regionRepository.save(region);
            regionDto.getCountiesIds().forEach(id->{
                County county = countyRepository.findById(id).orElseThrow();
                county.setRegion(region);
                countyRepository.save(county);
            });
            return savedRegion;
        } catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "region already exists!");
        }
    }

    public Region updateRegion(Integer regionId, RegionDto regionDto) {
        try {
            Region region = regionRepository.findById(regionId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found"));

            // Aktualizacja nazwy regionu
            region.setName(regionDto.getName());

            // Usunięcie powiązań z poprzednimi hrabstwami
            countyRepository.findByRegion(region).forEach(county -> county.setRegion(null));

            // Utworzenie nowych powiązań z hrabstwami
            regionDto.getCountiesIds().forEach(countyId -> {
                County county = countyRepository.findById(countyId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "County not found"));
                county.setRegion(region);
                countyRepository.save(county);
            });

            // Zapisanie zaktualizowanego regionu
            return regionRepository.save(region);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Failed to update region");
        }
    }

    private String convertListToJson(List<Integer> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            // Handle the exception
            e.printStackTrace();
            return null;
        }
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }



}
