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
@Table(name="굿즈구매내역")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="goods_id")
    private Goods goods;

    @OneToOne
    @JoinColumn(name="user_name")
    private User user;

    @Column(name="purchase_date",nullable = false)
    private LocalDate purchaseDate;

}