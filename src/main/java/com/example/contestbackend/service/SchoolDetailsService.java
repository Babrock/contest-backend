package com.example.contestbackend.service;

import com.example.contestbackend.dto.SchoolDetailsInfo;
import com.example.contestbackend.model.SchoolDetails;
import com.example.contestbackend.model.User;
import com.example.contestbackend.repository.SchoolDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolDetailsService {
    private final SchoolDetailsRepository schoolDetailsRepository;

    private final CategoryService categoryService;

    private final TitleService titleService;

    private final SchoolTypeService schoolTypeService;

    private final RegionService regionService;

    public List<SchoolDetails> getSchoolsDetails() {
        return schoolDetailsRepository.findAll();
    }

    public SchoolDetails getSchoolDetails(Integer id){
       return schoolDetailsRepository.findById(id).orElseThrow();
    }

    public SchoolDetails addSchoolDetails(SchoolDetailsInfo schoolDetailsInfo){
        String firstname = schoolDetailsInfo.getHeadmaster().getFirstname();
        String lastname = schoolDetailsInfo.getHeadmaster().getLastname();
        SchoolDetails schoolDetails = new SchoolDetails(
            schoolDetailsInfo.getSchoolComplex(),
            categoryService.getCategory(schoolDetailsInfo.getCategory()),
            schoolTypeService.getSchoolType(schoolDetailsInfo.getSchoolType()),
            titleService.getTitle(schoolDetailsInfo.getHeadmaster().getTitle()),
            regionService.getRegion(schoolDetailsInfo.getRegion()),
            firstname.substring(0,1).toUpperCase() + firstname.substring(1).toLowerCase(),
            lastname.substring(0,1).toUpperCase() + lastname.substring(1).toLowerCase(),
            schoolDetailsInfo.getHeadmaster().getEmail()
        );
        try {
            return schoolDetailsRepository.save(schoolDetails);
        } catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "SchoolDetails already exists!");
        }
    }

}
