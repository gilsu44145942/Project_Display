package com.dw.artgallery.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketDTO {
    private Long id;
    private int count;  // ✅ 티켓 개수를 숫자로 변경
    private LocalDate selectDate;
    private String artistGalleryTitle;  // ✅ 연관된 전시회 제목만 포함
    private LocalDate purchaseDate;
}
