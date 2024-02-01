package com.example.contestbackend.dto.projections;

public interface FormVerifyView {
    Integer getId();
    Boolean getIsAccepted();
    SchoolVerifyView getSchool();
    SchoolDetailsVerifyView getSchoolDetails();
    UserVerifyView getUser();
}
