package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="goods_sum")
    private GoodsCart goodsCart;

    @OneToOne
    @JoinColumn(name="goods_id")
    private Goods goods;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="purchase_date",nullable = false)
    private LocalDate purchaseDate;



}