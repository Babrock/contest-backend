package com.example.contestbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_school_class")
    private SchoolClass schoolClass;
    private String name;
    private BigDecimal score;

    public Task(String name, BigDecimal score, SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        this.name = name;
        this.score = score;
    }

    public Task(Integer id, String name, BigDecimal score, SchoolClass schoolClass) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.schoolClass = schoolClass;
    }
}
