package com.example.contestbackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "utils")
public class UtilsData {
    @Id
    @Column(name = "id_utils")
    private Integer id = 1;

    private boolean isSchoolsDownloaded = false;
}
