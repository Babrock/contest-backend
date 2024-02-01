package com.example.contestbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "school_details")
public class SchoolDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_school_detail")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @Column(name = "school_complex")
    private String schoolComplex;
    @ManyToOne
    @JoinColumn(name = "id_type_of_school")
    private SchoolType schoolType;
    @ManyToOne
    @JoinColumn(name = "id_title")
    private Title title;

    @ManyToOne
    @JoinColumn(name = "id_region")
    private Region region;

    @Column(name = "headmaster_firstname")
    private String firstname;

    @Column(name = "headmaster_lastname")
    private String lastname;
    @Column(name = "headmaster_email")
    private String email;

    public SchoolDetails(String schoolComplex, Category category, SchoolType schoolType, Title title, Region region, String firstname, String lastname, String email) {
        this.schoolComplex = schoolComplex;
        this.category = category;
        this.schoolType = schoolType;
        this.title = title;
        this.region = region;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

}
