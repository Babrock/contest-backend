package com.example.contestbackend.controller;

import com.example.contestbackend.dto.SchoolClassDto;
import com.example.contestbackend.dto.projections.SchoolClassTableView;
import com.example.contestbackend.dto.projections.SchoolClassVerifyView;
import com.example.contestbackend.model.Form;
import com.example.contestbackend.model.School;
import com.example.contestbackend.model.SchoolClass;
import com.example.contestbackend.service.SchoolClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
public class SchoolClassController {
    private final SchoolClassService schoolClassService;

    @GetMapping
    public List<SchoolClass> getClasses() {
        return  schoolClassService.getSchoolsClasses();
    }

    @GetMapping(params = "form_id")
    public List<SchoolClassVerifyView> getSchoolClassesByFormId(@RequestParam(name = "form_id") Integer id){
        return schoolClassService.getSchoolClassesVerifyViewByFormId(id);
    }

    @GetMapping("/accepted")
    public List<SchoolClassTableView> getSchoolClassesByFormAccepted(){
        return schoolClassService.getSchoolClassesByAcceptedForm();
    }

    @PostMapping
    public List<SchoolClass> addSchoolClasses(@RequestBody List<SchoolClassDto> schoolClassDtos, Form form, School school) {
        List<SchoolClass> addedClasses = new ArrayList<>();
        for (SchoolClassDto schoolClassDto : schoolClassDtos) {
            addedClasses.add(schoolClassService.addSchoolClass(schoolClassDto, form, school));
        }
        return addedClasses;
    }
}
