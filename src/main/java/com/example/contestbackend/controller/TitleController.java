package com.example.contestbackend.controller;

import com.example.contestbackend.model.Title;
import com.example.contestbackend.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/titles")
public class TitleController {
    private final TitleService titleService;

    @GetMapping
    public List<Title> getTitles() {return  titleService.getTitles();}
}
