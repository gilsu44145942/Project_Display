package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="goods_cart")
public class GoodsCart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="amount")
    private int amount;

    @Column(name="sum")
    private double sum;

    @OneToOne
    @JoinColumn(name="goods_id")
    private Goods goods;
    
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;






}