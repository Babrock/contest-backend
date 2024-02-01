package com.example.contestbackend.controller;

import com.example.contestbackend.model.SchoolDetails;
import com.example.contestbackend.service.SchoolDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school-details")
public class SchoolDetailsController {
    private final SchoolDetailsService schoolDetailsService;

    @GetMapping()
    public List<SchoolDetails> getSchoolsDetails(){
        return schoolDetailsService.getSchoolsDetails();
    }

    @GetMapping("/{id}")
    public SchoolDetails getSchoolDetails(@PathVariable Integer id){
        return schoolDetailsService.getSchoolDetails(id);
    }


}
