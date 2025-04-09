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
@Table(name="art")
public class Art {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="img_url",nullable = false)
    private String imgUrl;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name="completion_date",nullable = false)
    private LocalDate completionDate;

    @Column(name = "upload_date", nullable = false)
    private LocalDate uploadDate;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist;




}
