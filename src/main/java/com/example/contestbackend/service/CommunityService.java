package com.example.contestbackend.service;

import com.example.contestbackend.dto.CommunityRequest;
import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.model.Community;
import com.example.contestbackend.model.County;
import com.example.contestbackend.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    private final CountyService countyService;
    public List<Community> getCommunities() {return communityRepository.findAll();}

    public List<DictionaryResponse> findAllByCountyId(Integer county) {
        return communityRepository.findAllByCountyId(county);
    }

    public void addAll(List<CommunityRequest> requests) {
        List<Community> communities = requests.stream().map(communityRequest -> {
            County county = countyService.getCountyByName(communityRequest.getCountyName());
            Community community = new Community(communityRequest.getCommunityName(),county);
            return community;
        }).collect(Collectors.toList());
        communityRepository.saveAll(communities);
    }

    public Community saveCommunity(Community community) {
        return communityRepository.save(community);
    }
}
