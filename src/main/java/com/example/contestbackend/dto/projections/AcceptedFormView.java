package com.example.contestbackend.dto.projections;

public interface AcceptedFormView {
    Integer getId();
    SchoolVerifyView getSchool();
    SchoolDetailsVerifyView getSchoolDetails();
    UserVerifyView getUser();
}
