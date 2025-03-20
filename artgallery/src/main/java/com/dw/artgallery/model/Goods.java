package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="굿즈_상품")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="img_url")
    private String imgUrl;

    @Column(name = "description")
    private String description;

    @Column(name="price")
    private double price;

    @Column(name="stock")
    private int stock;


}
