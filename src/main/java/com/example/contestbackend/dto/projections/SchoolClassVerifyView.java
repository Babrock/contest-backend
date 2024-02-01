package com.example.contestbackend.dto.projections;

import com.example.contestbackend.dto.projections.NameView;

public interface SchoolClassVerifyView {
    NameView getTitle();
    String getFirstname();
    String getLastname();
    String getName();
    Integer getStudents();
    NameView getLanguage();
}
