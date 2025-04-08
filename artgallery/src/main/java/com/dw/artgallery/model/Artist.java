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

    @OneToMany(mappedBy = "artist")
    private List<Art> artList = new ArrayList<>();

    @ManyToMany(mappedBy = "artistList")
    private List<ArtistGallery> artistGalleryList = new ArrayList<>();

}
