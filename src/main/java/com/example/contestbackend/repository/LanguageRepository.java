package com.example.contestbackend.repository;

import com.example.contestbackend.model.Language;
import com.example.contestbackend.service.LanguageService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
