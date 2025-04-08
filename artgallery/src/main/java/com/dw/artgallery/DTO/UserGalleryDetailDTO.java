package com.dw.artgallery.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserGalleryDetailDTO {

    private String title;
    private String posterUrl;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String price;
    private List<String> userList = new ArrayList<>();
    private List<String> drawingImg = new ArrayList<>();

}
