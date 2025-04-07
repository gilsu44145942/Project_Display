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
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="count")
    private String count;

    @Column(name = "select_date")
    private LocalDate selectDate;

    @ManyToOne
    @JoinColumn(name = "artistGallery_id")
    private ArtistGallery artistGallery;


    @Column(name="purchase_date",nullable = false)
    private LocalDate purchaseDate;

}
