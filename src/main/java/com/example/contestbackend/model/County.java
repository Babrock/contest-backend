package com.example.contestbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "counties")
@RequiredArgsConstructor
@NoArgsConstructor
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_county")
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_voivodeship")
    private Voivodeship voivodeship;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_region")
    private Region region;
}
