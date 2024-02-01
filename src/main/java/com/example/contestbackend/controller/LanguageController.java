package com.example.contestbackend.controller;

import com.example.contestbackend.model.Language;
import com.example.contestbackend.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public List<Language> getLanguages (){return  languageService.getLanguages();}

    @GetMapping("/{id}")
    public Language getLangugage (@PathVariable Integer id){ return languageService.getLanguage(id);}

}
