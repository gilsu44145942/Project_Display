package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="굿즈상품")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="img_url",nullable = false)
    private List<String> imgUrlList;

    @Column(name = "description",nullable = false)
    private String description;

//    @Column(name = "option")
//    private List<String> option;

    @Column(name="price",nullable = false)
    private double price;

    @Column(name="stock",nullable = false)
    private int stock;


}
