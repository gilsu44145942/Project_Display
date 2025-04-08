package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="goods_id")
    private Goods goods;

    @ManyToMany
    @JoinTable(name = "goods_cart_user",
            joinColumns = @JoinColumn(name = "goods_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> User = new ArrayList<>();






}