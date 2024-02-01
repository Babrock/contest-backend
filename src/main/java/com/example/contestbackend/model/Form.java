package com.example.contestbackend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "forms")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_form")
    private Integer id;

    private Instant uploadDate;

    private Instant acceptedDate;

    @Column(name = "is_accepted")
    private Boolean isAccepted;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_school")
    private School school;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_schoolDetail")
    private SchoolDetails schoolDetails;

    public Form (User user, School school, Boolean isAccepted, SchoolDetails schoolDetails){
        uploadDate = Instant.now();
        this.user = user;
        this.school = school;
        this.isAccepted = isAccepted;
        this.schoolDetails = schoolDetails;
    }


}
