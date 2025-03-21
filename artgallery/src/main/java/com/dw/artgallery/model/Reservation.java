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
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    @ManyToOne
    @JoinColumn(name = "artistGallery_id")
    private ArtistGallery artistGallery;

    @Column(name = "reservation_date",nullable = false)
    private LocalDate reservationDate;

    @OneToMany
    @JoinColumn(name = "reservation")
    private List<Ticket> ticketList = new ArrayList<>();

}

