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
public class ArtUpdateDTO {
    private String title;
    private String imgUrl;
    private String description;
    private LocalDate completionDate;
    private LocalDate uploadDate;
    private Long artistId; // 작가 변경 가능성을 고려해 추가
}
