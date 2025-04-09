package com.dw.artgallery.DTO;


import com.dw.artgallery.model.Art;
import com.dw.artgallery.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtDetailDTO {

    private Long id;
    private String title;
    private String imgUrl;
    private String description;

    public static ArtDetailDTO fromEntity(Art art){
        return new ArtDetailDTO(
                art.getId(),
                art.getTitle(),
                art.getImgUrl(),
                art.getDescription());
    }

    public Art toEntity(Artist artist) {
        Art art = new Art();
        art.setId(this.id);
        art.setTitle(this.title);
        art.setImgUrl(this.imgUrl);
        art.setDescription(this.description);
        art.setArtist(artist);
        return art;
    }
}
