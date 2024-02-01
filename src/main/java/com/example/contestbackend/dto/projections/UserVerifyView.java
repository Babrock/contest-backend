package com.example.contestbackend.dto.projections;

import com.example.contestbackend.dto.projections.NameView;

public interface UserVerifyView {
    NameView getTitle();
    String getFirstname();
    String getLastname();
    String getEmail();
    String getPhone();
    Boolean getWantsToRate();
}
