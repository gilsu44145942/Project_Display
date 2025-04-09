package com.dw.artgallery.DTO;

import com.dw.artgallery.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtistDTO {

    private Long id;
    private String name;
    private String profile_img;
    private String description;
    private List<BiographyDTO> biographyList;


    public static ArtistDTO fromEntity(Artist artist) {
        return new ArtistDTO(
                artist.getId(),
                artist.getName(),
                artist.getProfile_img(),
                artist.getDescription(),
                artist.getBiographyList().stream()
                        .map(BiographyDTO::fromEntity).toList()
        );
    }
}