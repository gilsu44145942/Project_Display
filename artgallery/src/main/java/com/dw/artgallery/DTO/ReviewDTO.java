package com.dw.artgallery.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    private Long id;

    private String text;


    private String rating; // ENUM 수정 필요


    private LocalDateTime addDate;  // 작성일


    private LocalDateTime modifiedDate; // 수정일

    private String userName; // 유저 - 리뷰 (단방향)


    private String productId;


}
