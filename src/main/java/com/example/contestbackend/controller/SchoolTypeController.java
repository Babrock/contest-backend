package com.example.contestbackend.controller;

import com.example.contestbackend.model.SchoolType;
import com.example.contestbackend.service.SchoolTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school-types")
public class SchoolTypeController {
    private final SchoolTypeService schoolTypeService;

    @GetMapping
    public List<SchoolType> getAllSchoolTypes(){
        return schoolTypeService.getSchoolTypes();
    }
}
