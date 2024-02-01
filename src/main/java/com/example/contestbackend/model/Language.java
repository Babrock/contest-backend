package com.example.contestbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "languages")
@RequiredArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_language")
    private Integer id;

    @NonNull
    private String name;
}
