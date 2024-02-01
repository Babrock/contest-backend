package com.example.contestbackend.service;

import com.example.contestbackend.dto.SchoolClassDto;
import com.example.contestbackend.dto.projections.SchoolClassTableView;
import com.example.contestbackend.dto.projections.SchoolClassVerifyView;
import com.example.contestbackend.model.Form;
import com.example.contestbackend.model.School;
import com.example.contestbackend.model.SchoolClass;
import com.example.contestbackend.repository.SchoolClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;
    private final TitleService titleService;
    private final LanguageService languageService;
    public List<SchoolClass> getSchoolsClasses() {return schoolClassRepository.findAll();}
    public SchoolClass getSchoolClass(int id) {
        return schoolClassRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Class with given id not found"));
    }
    public List<SchoolClassVerifyView> getSchoolClassesVerifyViewByFormId(Integer formId) {
        return schoolClassRepository.findByFormId(formId);
    }

    public List<SchoolClassTableView> getSchoolClassesByAcceptedForm(){
        return schoolClassRepository.findByForm_IsAccepted(true);
    }

    public void deleteSchoolClassesByFormId(Integer id){
        schoolClassRepository.deleteAllByFormId(id);
    }

    public SchoolClass addSchoolClass(SchoolClassDto schoolClassDto, Form form, School school) {
        String firstname = schoolClassDto.getFirstname();
        String lastname = schoolClassDto.getLastname();
        SchoolClass schoolClass = new SchoolClass(
                titleService.getTitle(schoolClassDto.getTitle()),
                firstname.substring(0,1).toUpperCase() + firstname.substring(1).toLowerCase(),
                lastname.substring(0,1).toUpperCase() + lastname.substring(1).toLowerCase(),
                schoolClassDto.getName().toUpperCase(),
                schoolClassDto.getStudents(),
                languageService.getLanguage(schoolClassDto.getLanguage()),
                form,
                school
        );
        try {
            return schoolClassRepository.save(schoolClass);
        } catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, NestedExceptionUtils.getMostSpecificCause(e).getMessage());
        }
    }

//    public SchoolClass createSchoolClass(SchoolClassDto schoolClassDto, School school) {
//        SchoolClass schoolClass = new SchoolClass(
//                titleService.getTitle(schoolClassDto.getTitle()),
//                schoolClassDto.getFirstname(),
//                schoolClassDto.getLastname(),
//                schoolClassDto.getName(),
//                schoolClassDto.getStudents(),
//                languageService.getLanguage(schoolClassDto.getLanguage())
//        );
//        schoolClass.setSchool(school);
//        try {
//            return schoolClassRepository.save(schoolClass);
//        } catch (DataAccessException e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, NestedExceptionUtils.getMostSpecificCause(e).getMessage());
//        }
//    }





}
