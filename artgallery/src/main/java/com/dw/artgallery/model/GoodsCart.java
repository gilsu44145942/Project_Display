package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="굿즈_장바구니")
public class GoodsCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
