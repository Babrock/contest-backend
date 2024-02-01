package com.example.contestbackend.controller;

import com.example.contestbackend.dto.CommunityRequest;
import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.model.Community;
import com.example.contestbackend.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/communities")
public class CommunityController {
    private final CommunityService communityService;

    @GetMapping
    public List<Community> getCommunities () {return communityService.getCommunities();}

    @GetMapping(params = "county")
    public List<DictionaryResponse> getAllCommunitiesByIdCounties(@RequestParam Integer county){
        return communityService.findAllByCountyId(county);
    }

    @PostMapping
    void add(@RequestBody List<CommunityRequest> requests){
        communityService.addAll(requests);
    }
}
