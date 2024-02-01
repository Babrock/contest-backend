package com.example.contestbackend.service;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.dto.SchoolDetails;
import com.example.contestbackend.dto.SchoolInfo;
import com.example.contestbackend.model.School;
import com.example.contestbackend.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<School> getSchools(String city){
        return schoolRepository.findAllByCity(city, School.class);
    }


    public School getSchool(int id){
        return schoolRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "School with given id not found"));
    }
    public SchoolDetails getSchoolDetails(int id) {
        return schoolRepository.findById(id, SchoolDetails.class)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "School with given id not found"));
    }

    public List<DictionaryResponse> getDictionary(String city) {
        return schoolRepository.findAllByCity(city, DictionaryResponse.class);
    }
    public School addSchool(SchoolInfo schoolInfo) {
        School school = new School();
        school.setName(schoolInfo.getName());
        school.setVoivodeship(schoolInfo.getVoivodeship());
        school.setCounty(schoolInfo.getCounty());
        school.setCommunity(schoolInfo.getCommunity());
        school.setCity(schoolInfo.getCity());
        school.setPhone(schoolInfo.getPhone());
        school.setEmail(schoolInfo.getEmail());
        school.setStreet(schoolInfo.getStreet());
        school.setAddress(schoolInfo.getAddress());
        school.setApartmentNumber(schoolInfo.getApartmentNumber());
        school.setZipCode(schoolInfo.getZipCode());
        school.setPost(schoolInfo.getPost());
        school.setHeadmasterFullName(schoolInfo.getHeadmasterFullName());
        try {
            return schoolRepository.save(school);
        } catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "school already exists!");
        }
    }

    public School saveSchool(School school){
        return schoolRepository.save(school);
    }
}
