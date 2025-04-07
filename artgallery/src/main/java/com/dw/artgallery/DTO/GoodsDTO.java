package com.dw.artgallery.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodsDTO {
    private Long id;

    private String name;

    private List<String> imgUrlList;

    private String description;

    private double price;

    private int stock;
}
