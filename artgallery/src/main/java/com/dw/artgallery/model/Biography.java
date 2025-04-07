package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "biography")
public class Biography {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "award", nullable = false)
    private String award;

    @Column(name = "year", nullable = false) //수상연도
    private LocalDate year;

    @ManyToOne
    @JoinColumn(name = "artist")
    private Artist artist;


}
