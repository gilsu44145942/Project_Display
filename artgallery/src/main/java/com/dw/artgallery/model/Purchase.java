package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name="goods_cart_id")
    private GoodsCart goodsCart;

    @ManyToOne
    @JoinColumn(name="goods_id")
    private Goods goods;

    @ManyToMany
    @JoinTable(name = "purchase_user",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> User = new ArrayList<>();

    @Column(name="purchase_date",nullable = false)
    private LocalDate purchaseDate;



}