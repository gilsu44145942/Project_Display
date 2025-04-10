package com.dw.artgallery.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DrawingAddDTO {
    private String title;
    private String description;
    private String imgUrl;
    private Boolean isComplete;
}
