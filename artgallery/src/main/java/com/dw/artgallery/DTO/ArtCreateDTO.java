package com.dw.artgallery.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

    public class ArtCreateDTO {
        private String title;
        private String imgUrl;
        private String description;
        private LocalDate completionDate;
        private LocalDate uploadDate;
        private Long artistId; // artistId를 받아야 DB 저장 가능
    }

