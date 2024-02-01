package com.example.contestbackend.dto;

import java.util.List;

public record FormRequest (SchoolInfo schoolInfo, SchoolData schoolData, List<SchoolClassDto> schoolClasses, SchoolDetailsInfo schoolDetailsInfo) {
    public List<SchoolClassDto> schoolClasses() {
        return schoolClasses;
    }

}
