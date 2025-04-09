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

    @Column(name = "is_deleted", nullable = false)
    private Boolean deleted=false;


    public Art(String title, String imgUrl, String description, LocalDate completionDate, LocalDate uploadDate, Artist artist) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.description = description;
        this.completionDate = completionDate;
        this.uploadDate = uploadDate;
        this.artist = artist;
    }
}
