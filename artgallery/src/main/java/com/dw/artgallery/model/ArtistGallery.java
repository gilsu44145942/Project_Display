package com.dw.artgallery.model;

import com.dw.artgallery.DTO.ArtistGalleryDTO;
import com.dw.artgallery.DTO.ArtistGalleryDetailDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "artist_gallery")
public class ArtistGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "poster_url", nullable = false)
    private String posterUrl;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "price")
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

    public ArtistGalleryDTO toDto() {
        ArtistGalleryDTO artistGalleryDTO = new ArtistGalleryDTO();
        artistGalleryDTO.setTitle(this.title);
        artistGalleryDTO.setPosterUrl(this.posterUrl);
        artistGalleryDTO.setDescription(this.description);
        artistGalleryDTO.setStartDate(this.startDate);
        artistGalleryDTO.setEndDate(this.endDate);
        return artistGalleryDTO;
    }

    public ArtistGalleryDetailDTO TODTO() {
        ArtistGalleryDetailDTO artistGalleryDetailDTO = new ArtistGalleryDetailDTO();
        artistGalleryDetailDTO.setTitle(this.title);
        artistGalleryDetailDTO.setPosterUrl(this.posterUrl);
        artistGalleryDetailDTO.setDescription(this.description);
        artistGalleryDetailDTO.setStartDate(this.startDate);
        artistGalleryDetailDTO.setEndDate(this.endDate);
        List<String> artistList1 = new ArrayList<>();
        for (Artist data : artistList) {
            artistList1.add(data.getName());
        }
        artistGalleryDetailDTO.setArtistList(artistList1);

        List<String> artPoster1 = new ArrayList<>();
        List<String> artTitle1 = new ArrayList<>();
        List<String> artistName1 = new ArrayList<>();
        List<LocalDate> completionDate1 = new ArrayList<>();
        for (Art data : artList) {
            artPoster1.add(data.getImgUrl());
            artTitle1.add(data.getTitle());
            artistName1.add(data.getArtist().getName());
            completionDate1.add(data.getCompletionDate());
        }
        artistGalleryDetailDTO.setArtPoster(artPoster1);
        artistGalleryDetailDTO.setArtTitle(artTitle1);
        artistGalleryDetailDTO.setArtistName(artistName1);
        artistGalleryDetailDTO.setCompletionDate(completionDate1);
        return artistGalleryDetailDTO;
    }




}
