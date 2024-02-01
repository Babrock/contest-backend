package com.example.contestbackend.dto;

import com.example.contestbackend.model.Category;
import com.example.contestbackend.model.City;
//import com.example.contestbackend.model.Headmaster;
import com.example.contestbackend.model.SchoolType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SchoolInfo {
    @NotBlank
    private String name;
    private String voivodeship;
    private String county;
    private String community;
    private String city;
    private String phone;
    private String email;
    private String street;
    private String address;
    private String apartmentNumber;
    private String zipCode;
    private String post;
    private String headmasterFullName;
}
