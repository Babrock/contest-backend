package com.example.contestbackend.service;

import com.example.contestbackend.model.Language;
import com.example.contestbackend.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;

    public List<Language> getLanguages() {return  languageRepository.findAll();}

    public Language getLanguage(int id) {
        return languageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Language with given id not found"));
    }
}
