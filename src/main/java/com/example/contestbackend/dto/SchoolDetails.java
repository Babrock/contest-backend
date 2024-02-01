package com.example.contestbackend.dto;


public record SchoolDetails(
        String street,
        String address,
        String apartmentNumber,
        String zipCode,
        String post,
        String phone,
        String email,
        String headmasterFullName
        ){}
