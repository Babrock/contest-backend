package com.example.contestbackend.service;

import com.example.contestbackend.repository.UtilsDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UtilsDataService {
    private final UtilsDataRepository utilsDataRepository;
    @Transactional
    public void setIsSchoolsDownloaded(boolean isSchoolDownloaded){
        utilsDataRepository.getReferenceById(1).setSchoolsDownloaded(isSchoolDownloaded);
    }

    public boolean isSchoolsDownloaded(){
        return utilsDataRepository.findById(1).orElseThrow().isSchoolsDownloaded();
    }
}
