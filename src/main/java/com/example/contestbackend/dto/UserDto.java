package com.example.contestbackend.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private int title;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Boolean wantsToRate;
    private Boolean enabled;
    private String phone;
    private int role;

}