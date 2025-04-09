package com.dw.artgallery.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtCreateDTO {
    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotBlank(message = "이미지 URL을 입력하세요.")
    private String imgUrl;

    @NotBlank(message = "작품 설명을 입력하세요.")
    private String description;

    @NotNull(message = "작품 완성 날짜를 입력하세요.")
    private LocalDate completionDate;

    @NotNull(message = "업로드 날짜를 입력하세요.")
    private LocalDate uploadDate;

    @NotNull(message = "작가 ID를 입력하세요.")
    private Long artistId;
}
