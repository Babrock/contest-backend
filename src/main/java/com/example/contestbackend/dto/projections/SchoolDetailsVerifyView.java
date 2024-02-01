package com.example.contestbackend.dto.projections;

import com.example.contestbackend.dto.projections.NameView;

public interface SchoolDetailsVerifyView {
    String getSchoolComplex();
    NameView getCategory();
    NameView getSchoolType();
    NameView getRegion();
    NameView getTitle();
    String getFirstname();
    String getLastname();
    String getEmail();
}
