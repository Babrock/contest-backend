package com.example.contestbackend.dto.projections;

import com.example.contestbackend.dto.projections.CommunityView;
import com.example.contestbackend.dto.projections.NameView;

public interface SchoolVerifyView {
    String getName();
    String getVoivodeship();
    String getCounty();
    String getCommunity();
    String getCity();
    String getStreet();
    String getAddress();
    String getApartmentNumber();
    String getZipCode();
    String getPost();
    String getPhone();
    String getEmail();
}
