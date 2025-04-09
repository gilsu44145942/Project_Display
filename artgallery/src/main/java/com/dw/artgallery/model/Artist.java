package com.dw.artgallery.model;

import com.dw.artgallery.DTO.ArtistDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profile_img")
    private String profile_img;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Biography> biographyList = new ArrayList<>();

    @OneToMany(mappedBy = "artist")
    private List<Art> artList = new ArrayList<>();

    @ManyToMany(mappedBy = "artistList")
    private List<ArtistGallery> artistGalleryList = new ArrayList<>();

}
