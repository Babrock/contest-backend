package com.example.contestbackend.service;

import com.example.contestbackend.model.SchoolType;
import com.example.contestbackend.repository.SchoolTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolTypeService {
    private final SchoolTypeRepository schoolTypeRepository;

    public List<SchoolType> getSchoolTypes(){
        return schoolTypeRepository.findAll();
    }

    public SchoolType getSchoolType(int idSchoolType){
        return schoolTypeRepository.findById(idSchoolType).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"SchoolType not found"));
    }

    public SchoolType saveSchoolType(SchoolType schoolType) {
        return schoolTypeRepository.save(schoolType);
    }

    public List<SchoolType> getSchoolTypeByCategoryId(Integer categoryId){
        return schoolTypeRepository.findAllByCategoryId(categoryId);
    }
}
