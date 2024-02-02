package com.example.contestbackend.controller;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.dto.SchoolDetails;
import com.example.contestbackend.dto.SchoolInfo;
import com.example.contestbackend.model.School;
import com.example.contestbackend.model.User;
import com.example.contestbackend.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schools")
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping
    public List<School> getSchools(@PathVariable String city, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return schoolService.getSchoolsByCity(city);
    }

    @GetMapping(params = "city")
    public List<DictionaryResponse> getDictionaryByCity(String city){
        return schoolService.getDictionary(city);
    }

    @GetMapping("/{id}")
    SchoolDetails getSchoolById(@PathVariable int id) {
        return schoolService.getSchoolDetails(id);
    }

    @PostMapping()
    public School createSchool(@RequestBody SchoolInfo SchoolInfo){
        return schoolService.addSchool(SchoolInfo);
    }

}
