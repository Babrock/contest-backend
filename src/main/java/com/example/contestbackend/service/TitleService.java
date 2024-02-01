package com.example.contestbackend.service;

import com.example.contestbackend.model.Title;
import com.example.contestbackend.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final TitleRepository titleRepository;

    public List<Title> getTitles() {
        return titleRepository.findAll();
    }

    public Title getTitle(int idTitle){
        return titleRepository.findById(idTitle).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title not found"));
    }
}
