package com.dw.artgallery.DTO;

import com.dw.artgallery.model.Artist;
import com.dw.artgallery.model.Biography;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BiographyDTO {

    private Long id;
    private String award;
    private LocalDate year;

    public static BiographyDTO fromEntity(Biography bio) {
        return new BiographyDTO(bio.getId(), bio.getAward(), bio.getYear());
    }

    public Biography toEntity(Artist artist) {
        Biography bio = new Biography();
        bio.setId(this.id); // 수정 시 id 필요
        bio.setAward(this.award);
        bio.setYear(this.year);
        bio.setArtist(artist);
        return bio;
    }
}
