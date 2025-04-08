package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="artist_gallery")
public class ArtistGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name="poster_url",nullable = false)
    private String posterUrl;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name="price")
    private double price;

    @ManyToMany
    @JoinTable(name = "artist_gallery_artist",
            joinColumns = @JoinColumn(name = "artist_gallery_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> artistList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "artist_gallery_art",
            joinColumns = @JoinColumn(name = "artist_gallery_id"),
            inverseJoinColumns = @JoinColumn(name = "art_id"))
    private List<Art> artList = new ArrayList<>();

}
