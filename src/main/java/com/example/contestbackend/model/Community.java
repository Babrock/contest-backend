package com.example.contestbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "communities")
@RequiredArgsConstructor
@NoArgsConstructor
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_community")
    private Integer id;

    @NonNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_county")
    @NonNull
    private County county;
}
