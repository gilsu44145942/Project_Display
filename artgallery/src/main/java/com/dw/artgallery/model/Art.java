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
@Table(name="작품")
public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="img_url")
    private String imgUrl;

    @Column(name = "description")
    private String description;

    @Column(name="completion_date")
    private LocalDate completionDate;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist;



}
