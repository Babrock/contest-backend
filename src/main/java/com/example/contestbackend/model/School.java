package com.example.contestbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_school")
    private Integer id;
    private String name;
    private String voivodeship;
    private String county;
    private String community;
    private String city;
    private String street;
    private String address;
    @Column(name = "apartment_number")
    private String apartmentNumber;
    @Column(name = "zip_code")
    private String zipCode;
    private String post;
    private String phone;
    private String email;
    @Column(name = "headmaster_full_name")
    private String headmasterFullName;

}
