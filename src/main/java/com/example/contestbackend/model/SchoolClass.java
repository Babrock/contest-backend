package com.example.contestbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "schools_classes")
@NoArgsConstructor
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_school_class")
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_title")
    private Title title;
    private String firstname;
    private String lastname;
    @ManyToOne()
    @JoinColumn(name = "id_school")
    private School school;
    @ManyToOne()
    @JoinColumn(name = "id_form")
    private Form form;
    private Integer students;
    @ManyToOne()
    @JoinColumn(name = "id_language")
    private Language language;
    public SchoolClass(Title title, String firstname, String lastname, String name, int students,Language language, Form form, School school) {
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.name = name;
        this.students = students;
        this.language = language;
        this.form = form;
        this.school = school;
    }
}
