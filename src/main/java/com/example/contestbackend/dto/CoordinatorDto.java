package com.example.contestbackend.dto;

import javax.validation.constraints.NotBlank;

public record CoordinatorDto (@NotBlank(message = "title is required") Integer title, String firstname, String lastname, String email, String password, String phone, boolean wantsToRate, Integer role){}
