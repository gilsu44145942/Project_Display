package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="img_url",nullable = false)
    private String imgUrl;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name="price",nullable = false)
    private double price;

    @Column(name="stock",nullable = false)
    private int stock;


}
