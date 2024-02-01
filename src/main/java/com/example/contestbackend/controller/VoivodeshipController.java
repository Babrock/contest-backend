package com.example.contestbackend.controller;

import com.example.contestbackend.model.Voivodeship;
import com.example.contestbackend.service.VoivodeshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/voivodeships")
public class VoivodeshipController {
    private final VoivodeshipService voivodeshipService;

    @GetMapping
    public List<Voivodeship> getAll(){
        return voivodeshipService.getVoivodeships();
    }

    @GetMapping("/{id}")
    public List<Voivodeship> getVoivodeshipsNamesById(@PathVariable List<Integer> id){ return voivodeshipService.getVoivodeshipsNamesById(id); }


}
